import pygame
import random
import math
from pygame.locals import RLEACCEL

from screen import Screen


# Define the enemy object extending pygame.sprite.Sprite
# Instead of a surface, we use an image for a better looking sprite
class Umbrella(pygame.sprite.Sprite):
    Max_Speed = 10
    Min_Speed = 5

    def __init__(self):
        super(Umbrella, self).__init__()
        self.surf = pygame.image.load("icons/umbrella.png").convert()
        self.surf.set_colorkey((255, 255, 255), RLEACCEL)
        # The starting position is randomly generated, as is the speed
        self.rect = self.surf.get_rect(
            center=(
                random.randint(Screen.width + 20, Screen.width + 100),
                random.randint(0, Screen.height),
            )
        )
        self.speed = random.randint(self.Min_Speed, self.Max_Speed)
        self.time = 0

    # Move the bird based on speed
    # Remove it when when reaches the bottom of the screen
    def update(self):
        self.time += 1
        speed_x = -self.speed
        # Ajustamos la velocidad vertical para que se mueva de arriba a abajo
        speed_y = +2 # Modificamos el periodo
        self.rect.move_ip(speed_x, speed_y)

        # Si el objeto sale de la pantalla, lo eliminamos
        if self.rect.top > Screen.height:
            self.kill()

    def clone(self):
        return Umbrella()