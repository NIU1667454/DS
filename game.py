import pygame

# Import pygame.locals for easier access to key coordinates
# Updated to conform to flake8 and black standards
from pygame.locals import (
    K_ESCAPE,
    KEYDOWN,
    QUIT,
)

from screen import Screen
from player import Player
from FactorySprites import FactorySprites
import random

class Game:
    def __init__(self, flying_factory, landscape_factory):
        self._flying_factory = flying_factory
        self._landscape_factory = landscape_factory
        self._initialize_game()
        self._make_objects()
        self._load_music_and_sounds()
        self._play_music()

    def _initialize_game(self):
        # Setup the clock for a decent frame rate
        self._clock = pygame.time.Clock()
        # Create and get the screen object
        self._screen = pygame.display.set_mode((Screen.width, Screen.height))

        self._user_quits = False  # to quit press Escape or close the window

    def _make_objects(self):
        # Crear instancia del jugador
        self._player = Player()

        # Usar los grupos proporcionados por las fábricas
        self._flying_sprites = pygame.sprite.Group()
        self._landscape_sprites = pygame.sprite.Group()

        # Crear un grupo combinado para renderizado, incluyendo el jugador
        self._all_sprites = pygame.sprite.Group(self._player, self._flying_sprites, self._landscape_sprites)

    @staticmethod
    def _play_music():
        pygame.mixer.music.play(loops=-1)

    def _load_music_and_sounds(self):
        # Load and play our background music
        pygame.mixer.music.load("sounds_music/Apoxode_-_Electric_1.mp3")
        self._collision_sound = pygame.mixer.Sound("sounds_music/Explosion_10.ogg")
        self._collision_sound.set_volume(0.5)

    def _process_event(self):
        # Look at every event in the queue
        for event in pygame.event.get():
            print('event type = {}'.format(event.type))
            # Did the user hit a key?
            if event.type == KEYDOWN:
                # Was it the Escape key? If so, stop the loop
                if event.key == K_ESCAPE:
                    self._user_quits = True
            # Did the user click the window close button? If so, stop the loop
            elif event.type == QUIT:
                self._user_quits = True
            # Should we add a new bird?
            elif event.type in self._flying_factory.eventTypes.values():
                # Use factoryFlying to create a flying sprite
                sprite_type = random.choice(["bird", "umbrella", "jet", "missile"])
                new_flying_sprite = self._flying_factory.make(sprite_type)
                if new_flying_sprite:
                    self._flying_sprites.add(new_flying_sprite)
                    self._all_sprites.add(new_flying_sprite)
            elif event.type in self._landscape_factory.eventTypes.values():
                # Use factoryLandscape to create a landscape sprite
                sprite_type = random.choice(["cloud", "mountain"])
                new_landscape_sprite = self._landscape_factory.make(sprite_type)
                if new_landscape_sprite:
                    self._landscape_sprites.add(new_landscape_sprite)
                    self._all_sprites.add(new_landscape_sprite)

    def _update(self):
        # Get the set of keys pressed and check for user input
        pressed_keys = pygame.key.get_pressed()
        self._player.update(pressed_keys)
        # move the player if key was an arrow
        # Update the position of our birds and clouds
        self._flying_sprites.update()
        self._landscape_sprites.update()

    def _draw(self):
        # Fill the screen.py with sky blue
        self._screen.fill((135, 206, 250))
        # Draw all our sprites
        for entity in self._all_sprites:
            self._screen.blit(entity.surf, entity.rect)
        # Flip everything to the display
        pygame.display.flip()

    def _collision(self):
        return pygame.sprite.spritecollideany(self._player, self._flying_sprites)

    def _game_over(self):
        return self._collision() or self._user_quits

    def _keep_frame_rate(self):
        # Ensure we maintain a 30 frames per second rate
        self._clock.tick(30)

    def play(self):
        while not self._game_over():
            self._process_event()
            self._update()
            self._draw()
            self._keep_frame_rate()

        # Check if any bird have collided with the player
        if self._collision():
            # If so, remove the player
            self._player.kill()
            # Stop any moving sounds and play the collision sound
            self._player.stop_move_sounds()
            pygame.mixer.music.stop()
            self._collision_sound.play()
            pygame.time.wait(2000)  # seconds, to play collision sound

        pygame.mixer.quit()
