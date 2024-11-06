import pygame
from abc import ABC, abstractmethod

class GameSprite(pygame.sprite):

    @abstractmethod
    def __init__(self):
        pass

    @abstractmethod
    def clone(self):
        pass