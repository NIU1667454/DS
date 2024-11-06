import pygame
import random
from bird import Bird
from cloud import Cloud
from Umbrella import Umbrella
from Mountain import Mountain

import pygame


class FactorySprites:
    def __init__(self, sprite_prototypes, sprite_periods, eventtypes):
        # Diccionari de prototips
        self.prototypes = {sprite.__class__.__name__.lower(): sprite for sprite in sprite_prototypes}

        # Diccionari de períodes per crear Sprites (ms)
        self.periods = {sprite.__class__.__name__.lower(): period for sprite, period in
                        zip(sprite_prototypes, sprite_periods)}

        # Diccionari de eventtypes
        self.eventTypes = {sprite.__class__.__name__.lower(): eventtypes + i for i, sprite in enumerate(sprite_prototypes)}

        # Temporitzadors per cada event
        for sprite_type, period in self.periods.items():
            pygame.time.set_timer(self.eventTypes[sprite_type], period)

    def make(self, sprite_type):
        # Clona el prototipo del sprite especificado, si existe
        if sprite_type in self.prototypes:
            return self.prototypes[sprite_type].clone()
        return None

