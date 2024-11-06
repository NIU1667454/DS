import pygame
from game import Game
from screen import Screen
from FactorySprites import FactorySprites
from bird import Bird
from Umbrella import Umbrella
from Mountain import Mountain
from cloud import Cloud
from Jet import Jet
from Missile import Missile

# Initialize PyGame
# setup for sounds_music, defaults are good
pygame.mixer.init()
pygame.init()
# create the screen object
pygame.display.set_mode((Screen.width, Screen.height))

level='impossible'

if level=='easy':
    # easy game, only birds and clouds
    factory_flying = FactorySprites([Bird()], [300], pygame.USEREVENT + 1)
    factory_landscape = FactorySprites([Cloud()], [400], pygame.USEREVENT + 10)
elif level=='difficult':
    #difficult game, with easy sprites + Umbrellas And Mountains
    factory_flying = FactorySprites([Bird(), Umbrella()], [400, 500], pygame.USEREVENT + 1)
    factory_landscape = FactorySprites([Cloud(), Mountain()], [500, 2000], pygame.USEREVENT + 10)
elif level=='impossible':
    #impossible game, with easy sprites + difficult + Jet And Missile
    factory_flying = FactorySprites([Bird(), Umbrella(), Jet(), Missile()], [400, 500, 600, 700], pygame.USEREVENT + 1)
    factory_landscape = FactorySprites([Cloud(), Mountain()], [500, 5000], pygame.USEREVENT + 10)
else:
    assert False

# start playing
game = Game(factory_flying, factory_landscape)
game.play()