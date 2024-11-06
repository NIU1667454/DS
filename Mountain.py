import pygame
import random
from pygame.locals import RLEACCEL

from screen import Screen

class Mountain(pygame.sprite.Sprite):
    def __init__(self):
        super(Mountain, self).__init__()
        self.surf = pygame.image.load("icons/mountain.png").convert()
        self.surf.set_colorkey((0, 0, 0), RLEACCEL)

        # La posición inicial se genera aleatoriamente en el borde inferior
        self.rect = self.surf.get_rect()

        self.rect.center = (
            random.randint(Screen.width + 20, Screen.width + 100),  # Posición horizontal inicial (fuera de pantalla)
            Screen.height - self.rect.height / 2  # Posición vertical en la parte inferior de la pantalla
        )

        self.speed = 5  # Velocidad de movimiento hacia arriba

    # Mover la montaña hacia arriba
    def update(self):
        self.rect.move_ip(-5, 0)  # Mover hacia izquierda
        # Limitar la posición para que no supere lado izquierdo pantalla
        if self.rect.right < 0:
            self.kill()  # Eliminar si supera la mitad de la pantalla

    def clone(self):
        return Mountain()
