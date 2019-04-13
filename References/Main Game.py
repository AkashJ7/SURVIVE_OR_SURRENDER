import pygame, random

pygame.init()

DISPLAY_HEIGHT, DISPLAY_WIDTH = 600, 800
DS = pygame.display.set_mode((DISPLAY_WIDTH , DISPLAY_HEIGHT))
clock = pygame.time.Clock()
pygame.display.set_caption("RETURN TO EARTH")

movingLeft = pygame.transform.scale(pygame.image.load("P.moveLeft.png").convert(), (13*2, 18*2))
movingLeft.set_colorkey((255, 255, 255, 255))
movingRight = pygame.transform.scale(pygame.image.load("P.moveRight.png").convert(), (13*2, 18*2))
movingRight.set_colorkey((255, 255, 255, 255))
standingStill = pygame.transform.scale(pygame.image.load("P.standing.png").convert(), (17, 18*2))
standingStill.set_colorkey((255, 255, 255, 255))
rocket = pygame.image.load("Spaceship.png").convert()
rocket.set_colorkey((255, 255, 255))
rocket_planet = pygame.image.load("Spaceship(Planet).png").convert()
rocket_planet.set_colorkey((255, 255, 255))
rocket_back = pygame.image.load("Spaceship(Intro3).png").convert()
rocket_back.set_colorkey((255, 255, 255))
rocket_crashed = pygame.transform.rotate(pygame.image.load("Spaceship(Crash-Landed).png").convert(), -135)
rocket_crashed.set_colorkey((255, 255, 255))
spaceship = rocket.get_rect()
spaceship.x, spaceship.y = spaceship.x - 200, spaceship.y - 100
storm_cloud = pygame.transform.scale(pygame.image.load("StormCloud.png").convert_alpha(), (1000, 200))
global island
island = pygame.transform.scale(pygame.image.load("IslandBackground.png").convert_alpha(), (600, 200))
island.set_colorkey((255, 255, 255, 255))
game_logo = pygame.transform.scale(pygame.image.load("Game Logo.png").convert(), (13*12, 18*12))
game_logo.set_colorkey((255, 255, 255, 255))
crushed = pygame.transform.scale(pygame.image.load("P.crushed.png").convert(), (116*3, 52*3))
crushed.set_colorkey((255, 255, 255, 255))
crushed_island = pygame.image.load("CrushedBackground.png").convert_alpha()
fell1 = pygame.transform.rotate(pygame.image.load("P.fell.png").convert(), -40)
fell = pygame.transform.scale(fell1, (fell1.get_rect().w//2, fell1.get_rect().h//2))
fell.set_colorkey((255, 255, 255, 255))
intro1 = pygame.transform.scale(pygame.image.load("Intro1.jpg").convert(), (800, 600))
intro2 = pygame.transform.scale(pygame.image.load("Intro2.jpg").convert(), (800, 600))
intro3 = pygame.transform.scale(pygame.image.load("Intro3.jpg").convert(), (800, 600))
intro4 = pygame.transform.scale(pygame.image.load("Intro4.jpg").convert(), (800, 600))
night_bg = pygame.image.load("Night Background.jpg").convert()
bricks = pygame.image.load("Brick Wall.png").convert()
background_x1 = 0
crusher = pygame.image.load("Metal Crusher.png").convert()
crusher.set_colorkey((255, 255, 255))
spikes = pygame.image.load("Ground Spikes.png").convert()
spikes.set_colorkey((255, 255, 255))
crushed1 = pygame.transform.scale(pygame.image.load("P.crushed(Metal).png").convert(), (116*3, 52*3))
crushed1.set_colorkey((255, 255, 255))
impaled = pygame.transform.scale(spikes, (spikes.get_rect().w*2, spikes.get_rect().h*2))
rocket_leaving = pygame.image.load("Spaceship(Leaving).png").convert()
rocket_leaving.set_colorkey((255, 255, 255))
sunset = pygame.transform.scale(pygame.image.load("Sunset Outro.jpg").convert(), (800, 600))
outro_standing = pygame.image.load("P.standing.png").convert()
outro_standing.set_colorkey((255, 255, 255))
box = pygame.transform.scale(pygame.image.load("Supply Crate.jpg").convert(), (200, 200))
direction = pygame.transform.scale(pygame.image.load("Lab DIrections.png").convert(), (int(650/1.5), int(220/1.5)))
direction.set_colorkey((255, 255, 255))

global arial30, arial30b, arial50b, arial20b, arial60b, mono30b, mono50b
arial30 = pygame.font.SysFont("arial", 30, False, False)
arial30b = pygame.font.SysFont("arial", 30, True, False)
arial50b = pygame.font.SysFont("arial", 50, True, False)
arial20b = pygame.font.SysFont("arial", 20, True, False)
arial60b = pygame.font.SysFont("arial", 60, True, False)
mono30b = pygame.font.SysFont("FreeMono, Monospace", 30, True, False)
mono50b = pygame.font.SysFont("FreeMono, Monospace", 50, True, False)

def text(fontz, message, rect, color, background=None, button=False):
    x = pygame.Rect(rect)
    y = pygame.Rect(pygame.mouse.get_pos()[0], pygame.mouse.get_pos()[1], 1, 1)
    if button == True and y.colliderect(x):
            if pygame.mouse.get_pressed()[0]:
                return True
            else:
                background -= 40            
    if background != None:
        pygame.draw.rect(DS, ((background, background, background)), x, 0)
    DS.blit(fontz.render(message, True, color), (x.x + 7, x.y + 2))

current_scene = 1

class Enemy:

    speed = 2
    
    def __init__(self, x = 0, tag = None):
        global boxCol_enemy
        if current_scene == 4.4 or current_scene == 5:
            self.x, self.y = random.randint(30, DISPLAY_WIDTH - 30), -100
            self.h, self.w = random.randint(40, 60), random.randint(40, 60)
            self.color = (random.randint(40, 140))
            asteroid = pygame.Surface((self.w, self.h))
            boxCol_enemy = asteroid.get_rect()
            boxCol_enemy.x, boxCol_enemy.y, boxCol_enemy.w, boxCol_enemy.h = self.x + self.w/6, self.y + self.h/6, 2*self.w/3, 2*self.h/3
        elif current_scene == 9.1 or current_scene == 9.2:
            self.x, self.y_in, self.tag = background_x1 + x, 5, tag
            self.x_location = self.x - background_x1
            if tag == 0: self.y, self.w, self.h = 515, 110, 27
            elif tag == 1: self.y, self.w, self.h = 450, 153, 60
            self.boxCol = pygame.Rect(self.x, self.y, self.w, self.h)

    def animate(self):
        if current_scene == 5:
            pygame.draw.ellipse(DS, (self.color, self.color, self.color), (boxCol_enemy.x - self.w/6, boxCol_enemy.y - self.h/6, self.w, self.h), 0)
            #pygame.draw.rect(DS, (255, 0, 255), boxCol_asteroid, 3)
            if (boxCol_enemy.y > 400 and boxCol_enemy.x > 120 and boxCol_enemy.x < 670) or boxCol_enemy.y > DISPLAY_HEIGHT + 50:
                self.__init__()
            else:
                boxCol_enemy.y += self.speed
            self.speed += 0.0012
        if current_scene == 9.2:
            if self.tag == 0:
                DS.blit(spikes, (self.boxCol.x - 7 + background_x1, self.boxCol.y - 7))
                #pygame.draw.rect(DS, (255, 0, 0), (self.boxCol.x + background_x1, self.boxCol.y, self.w, self.h), 3)
            elif self.tag == 1:
                DS.blit(crusher, (self.boxCol.x - 8 + background_x1, self.boxCol.y - 343))
                #pygame.draw.rect(DS, (255, 0, 0), (self.boxCol.x + background_x1, self.boxCol.y, self.w, self.h), 3)
                if not (350 < self.boxCol.y < 490):
                    self.y_in = -1/(2*self.y_in)
                self.boxCol.y += self.y_in

enemies = []

asteroid = Enemy()

class Character:
    
    def __init__(self):
        self.y = DISPLAY_HEIGHT/2 - 200
        self.speed = 3
        self.died = True
        self.crushed = False
        self.fell = False
        self.impaled = False
        self.time = 40
        self.distance = None
        self.jump = False
        self.jumping_time = 0
        self.jump_height = -(-0.02*self.jumping_time*self.jumping_time + 3.5*self.jumping_time)
        self.falling = False
        self.scroll_x = 50

        global boxCol_moving
        boxCol_moving = movingLeft.get_rect()
        boxCol_moving.x, boxCol_moving.y, boxCol_moving.w, boxCol_moving.h = 255, self.y, boxCol_moving.w/2 + 5, boxCol_moving.h - 2
        global boxCol_still
        boxCol_still = standingStill.get_rect()
        boxCol_still.x, boxCol_still.y, boxCol_still.w, boxCol_still.h = boxCol_moving.x, boxCol_moving.y, boxCol_still.w/2 + 5, boxCol_still.h - 2

    def move(self):
        if self.jump_height > 0:
            self.jump = False
            self.jumping_time = 0
        if pygame.key.get_pressed()[pygame.K_UP] and self.falling == False: self.jump = True
        if self.jump:
            self.jumping_time += 2
            self.jump_height = -(-0.03*self.jumping_time*self.jumping_time + 3.5*self.jumping_time)
        if current_scene == 4.4 or current_scene == 5 or current_scene == 5.1 or current_scene == 9.1 or current_scene == 9.29 or current_scene == 9.3:
            if (pygame.key.get_pressed()[pygame.K_LEFT] or pygame.key.get_pressed()[pygame.K_RIGHT]):
                if pygame.key.get_pressed()[pygame.K_LEFT]:
                    boxCol_moving.x -= self.speed
                    boxCol_still.x -= self.speed
                    DS.blit(movingLeft, (boxCol_moving.x - 5, boxCol_moving.y - 2 + self.jump_height))
                    #pygame.draw.rect(DS, (255, 0, 0, 128), (boxCol_moving.x, boxCol_moving.y + self.jump_height, boxCol_moving.w, boxCol_moving.h), 3)
                elif pygame.key.get_pressed()[pygame.K_RIGHT]:
                    boxCol_moving.x += self.speed
                    boxCol_still.x += self.speed
                    DS.blit(movingRight, (boxCol_moving.x - 5, boxCol_moving.y - 2 + self.jump_height))
                    #pygame.draw.rect(DS, (255, 0, 0, 128), (boxCol_moving.x, boxCol_moving.y + self.jump_height, boxCol_moving.w, boxCol_moving.h), 3)
                if current_scene == 5:
                    if boxCol_enemy.colliderect(boxCol_moving.x, boxCol_moving.y + self.jump_height, boxCol_moving.w, boxCol_moving.h):
                        self.died = True
                        self.crushed = True
            elif not (pygame.key.get_pressed()[pygame.K_LEFT] or pygame.key.get_pressed()[pygame.K_RIGHT]):
                DS.blit(standingStill, (boxCol_still.x - 2, boxCol_still.y - 2 + self.jump_height))
                if current_scene == 5:
                    if boxCol_enemy.colliderect(boxCol_still.x, boxCol_still.y + self.jump_height, boxCol_still.w, boxCol_still.h):
                        self.died = True
                        self.crushed = True
                #pygame.draw.rect(DS, (255, 0, 0, 128), (boxCol_still.x, boxCol_still.y + self.jump_height, boxCol_still.w, boxCol_still.h), 3)
        elif current_scene == 9.2:
            if (pygame.key.get_pressed()[pygame.K_LEFT] or pygame.key.get_pressed()[pygame.K_RIGHT]):
                if pygame.key.get_pressed()[pygame.K_LEFT]:
                    boxCol_moving.x -= self.speed
                    boxCol_still.x -= self.speed
                    self.scroll_x -= self.speed
                    DS.blit(movingLeft, (boxCol_moving.x - 5, boxCol_moving.y - 2 + self.jump_height))
                    #pygame.draw.rect(DS, (255, 0, 0, 128), (boxCol_moving.x, boxCol_moving.y + self.jump_height, boxCol_moving.w, boxCol_moving.h), 3)
                elif pygame.key.get_pressed()[pygame.K_RIGHT]:
                    boxCol_moving.x += self.speed
                    boxCol_still.x += self.speed
                    self.scroll_x += self.speed
                    DS.blit(movingRight, (boxCol_moving.x - 5, boxCol_moving.y - 2 + self.jump_height))
                    #pygame.draw.rect(DS, (255, 0, 0, 128), (boxCol_moving.x, boxCol_moving.y + self.jump_height, boxCol_moving.w, boxCol_moving.h), 3)
                for i in enemies:
                    if i.boxCol.colliderect(self.scroll_x, boxCol_moving.y + self.jump_height, boxCol_moving.w, boxCol_moving.h):
                        self.died = True
                        if i.tag == 0: self.impaled = True
                        elif i.tag == 1: self.crushed = True
            elif not (pygame.key.get_pressed()[pygame.K_LEFT] or pygame.key.get_pressed()[pygame.K_RIGHT]):
                DS.blit(standingStill, (boxCol_still.x - 2, boxCol_still.y - 2 + self.jump_height))
                for i in enemies:
                    if i.boxCol.colliderect(self.scroll_x, boxCol_still.y + self.jump_height, boxCol_still.w, boxCol_still.h):
                        self.died = True
                        if i.tag == 0: self.impaled = True
                        elif i.tag == 1: self.crushed = True
                #pygame.draw.rect(DS, (255, 0, 0, 128), (boxCol_still.x, boxCol_still.y + self.jump_height, boxCol_still.w, boxCol_still.h), 3)

    def constrain(self):
        if current_scene == 4.4 or current_scene == 5 or current_scene == 5.1:
            if ((boxCol_moving.y + self.jump_height >= DISPLAY_HEIGHT/2 + 82) or (boxCol_still.y + self.jump_height >= DISPLAY_HEIGHT/2 + 82)) and ((boxCol_moving.bottomright[0] >= 115 and boxCol_moving.bottomleft[0] <= 685) or (boxCol_still.bottomright[0] >= 115 and boxCol_still.bottomleft[0] <= 685)):
                boxCol_moving.y = DISPLAY_HEIGHT/2 + 82
                boxCol_still.y = DISPLAY_HEIGHT/2 + 82
                self.falling = False
            elif self.jump == False:
                self.falling = True
                boxCol_moving.y += 5
                boxCol_still.y += 5
            if boxCol_moving.y + self.jump_height >= DISPLAY_HEIGHT - 20:
                self.died = True
                self.fell = True
        elif current_scene == 9.1 or current_scene == 9.2 or current_scene == 9.29 or current_scene == 9.3:
            if boxCol_moving.y + self.jump_height >= DISPLAY_HEIGHT/2 + 200:
                boxCol_moving.y = DISPLAY_HEIGHT/2 + 200
                boxCol_still.y = DISPLAY_HEIGHT/2 + 200
                self.falling = False
            elif self.jump == False:
                self.falling = True
                boxCol_moving.y += 5
                boxCol_still.y += 5
            if current_scene == 9.2:
                if self.scroll_x >= DISPLAY_WIDTH/2:
                    boxCol_moving.x, boxCol_still.x = DISPLAY_WIDTH/2, DISPLAY_WIDTH/2
                else:
                    if boxCol_moving.x <= 10:
                        background_x1 = 0
                        self.scroll_x = 10
                        boxCol_moving.x = 10
                        boxCol_still.x = 10
                    boxCol_moving.x = self.scroll_x
            if current_scene == 9.29:
                if boxCol_moving.x <= 10:
                    boxCol_moving.x = 10
                    boxCol_still.x = 10

    def track_time(self):
        if self.time <= 0.009:
            self.time = 0
        else:
            self.time -= 0.009
        text(arial30b, ("TIME REMAINING: " + str(round(self.time, 3))), (20, 550, 315, 40), (180, 180, 180), 60, False)

    def track_distance(self):
        self.distance = (8500 - self.scroll_x)
        text(arial30b, "DISTANCE REMAINING: %s" % str(self.distance), (20, 550, 360, 40), (180, 180, 180), 60, False)

android = Character()

intro5_ran = False

def scene():
    global current_scene, intro1_ran, intro2_ran, intro3_ran, intro4_ran, intro5_ran, intro6_ran, explosion_radius, spaceship_scale, spaceship_scale_in, spaceship_launch_height, background_x1

    if current_scene == 1:
        DS.fill((15, 15, 40))
        DS.blit(game_logo, (450, 140))
        pygame.draw.line(DS, (255, 162, 0), (265, 190), (465, 190), 10)
        pygame.draw.circle(DS, (255, 162, 0), (265, 191), 5)
        pygame.draw.circle(DS, (255, 162, 0), (465, 191), 5)
        pygame.draw.line(DS, (255, 162, 0), (245, 250), (445, 250), 10)
        pygame.draw.circle(DS, (255, 162, 0), (245, 251), 5)
        pygame.draw.circle(DS, (255, 162, 0), (445, 251), 5)
        pygame.draw.line(DS, (255, 162, 0), (225, 310), (425, 310), 10)
        pygame.draw.circle(DS, (255, 162, 0), (225, 311), 5)
        pygame.draw.circle(DS, (255, 162, 0), (425, 311), 5)        
        text(arial60b, "RETURN TO EARTH!", (DISPLAY_WIDTH/2 - 501/2, 30, 501, 70), (255, 255, 255))
        if text(arial30, "PLAY", (DISPLAY_WIDTH/2 - 75/2, 420, 75, 41), (255, 255, 255), 100, True):
            current_scene = 4.1
            intro1_ran = False
        if text(arial30, "CONTROLS", (DISPLAY_WIDTH/2 - 150/2, 470, 150, 41), (255, 255, 255), 100, True):
            current_scene = 2
        if text(arial30, "CREDITS", (DISPLAY_WIDTH/2 - 122/2, 520, 122, 41), (255, 255, 255), 100, True):
            current_scene = 3
    elif current_scene == 4.1:
        if not intro1_ran:
            spaceship.x, spaceship.y = -300, 300
            explosion_radius = 0
            intro1_ran = True
        if spaceship.x >= 1:
            if explosion_radius >= 50:
                explosion_radius = 50
            else:
                explosion_radius += 1
        if spaceship.x >= 850:
            current_scene = 4.2
            intro2_ran = False
        else:
            DS.blit(intro1, (0, 0))
            if spaceship.x >= 50:
                text(mono30b, 'ROBOT: A BOOSTER HAS EXPLODED', (DISPLAY_WIDTH/2 - 535/2, 10, 535, 35), (50, 255, 50), 10)
            spaceship.move_ip(3, 0)
            DS.blit(standingStill, (spaceship.x + 247, spaceship.centery - 28))
            DS.blit(rocket, (spaceship.x, spaceship.y))
            pygame.draw.circle(DS, (255, 85, 0), (spaceship.x + 175, spaceship.centery - 28), explosion_radius, 0)
    elif current_scene == 4.2:
        if not intro2_ran:
            spaceship.x, spaceship.y = -200, 300
            intro2_ran = True
        if spaceship.x >= 330:
            current_scene = 4.3
            intro3_ran = False
        else:
            DS.blit(intro2, (0, 0))
            spaceship.move_ip(1, 0)
            DS.blit(pygame.transform.scale(standingStill, (standingStill.get_rect().w//2, standingStill.get_rect().h//2)), (spaceship.x + 125, spaceship.centery - 65))
            DS.blit(pygame.transform.scale(rocket, (spaceship.w//2, spaceship.h//2)), (spaceship.x, spaceship.y))
            text(mono30b, "ROBOT: UNTIL FURTHER REPAIR,", (DISPLAY_WIDTH/2 - 510/2, 10, 510, 35), (50, 255, 50), 10)
            text(mono30b, 'WE ARE FORCED TO CRASH', (DISPLAY_WIDTH/2 - 410/2, 45, 410, 35), (50, 255, 50), 10)
            text(mono30b, 'LAND ON THIS NEARBY PLANET', (DISPLAY_WIDTH/2 - 482/2, 80, 482, 35), (50, 255, 50), 10)
    elif current_scene == 4.3:
        if not intro3_ran:
            spaceship.x, spaceship.y = 300, 200
            spaceship_scale = 1
            spaceship_scale_in = 0.004
            intro3_ran = True
        if spaceship_scale <= 0.25:
            current_scene = 4.4
        else:
            DS.blit(intro3, (0, 0))
            DS.blit(pygame.transform.scale(rocket_back, (round(spaceship.w * spaceship_scale * 0.8), round(spaceship.h * spaceship_scale))), (spaceship.x, spaceship.y))
            spaceship_scale -= spaceship_scale_in/2
            spaceship_scale_in -= 0.000005
            text(mono30b, "EARTH: TO KEEP YOU SAFE, ABORT ON THE", (DISPLAY_WIDTH/2 - 680/2, 10, 680, 35), (50, 255, 50), 10)
            text(mono30b, "ARRIVING FLOATING ISLAND. YOU MUST SURVIVE", (DISPLAY_WIDTH/2 - 770/2, 45, 770, 35), (50, 255, 50), 10)
            text(mono30b, "40 SECONDS WHILE THE SPACESHIP CREATES", (DISPLAY_WIDTH/2 - 698/2, 80, 698, 35), (50, 255, 50), 10)
            text(mono30b, "A TEMPORARY BASE ON THE GROUND", (DISPLAY_WIDTH/2 - 554/2, 115, 554, 35), (50, 255, 50), 10)
    elif current_scene == 4.4:
        if android.died == True:
            spaceship.x, spaceship.y = -200, -100
            android.__init__()
            asteroid.__init__()
            android.crushed = False
            android.fell = False
            android.died = False
            asteroid.speed = 2
        spaceship.move_ip(28.97777/3.5, 7.7645/3.5)
        DS.blit(night_bg, (0,0))
        DS.blit(island, (DISPLAY_WIDTH/2 - 300, DISPLAY_HEIGHT - 210))
        DS.blit(storm_cloud,(-40, -80))
        if spaceship.x >= 50:
            android.move()
            android.constrain()
            if boxCol_still.y >= DISPLAY_HEIGHT/2 + 82:
                current_scene = 5
        else:
            DS.blit(pygame.transform.rotate(standingStill, -15), (spaceship.x + 265, spaceship.centery + 35))
        DS.blit(pygame.transform.rotate(rocket_planet, -15), (spaceship.x, spaceship.y))
    elif current_scene == 5:
        DS.blit(night_bg, (0, 0))
        if spaceship.x <= 810:
            spaceship.move_ip(28.97777/3.5, 7.7645/3.5)
            DS.blit(pygame.transform.rotate(rocket_planet, -15), (spaceship.x, spaceship.y))
        android.constrain()
        android.move()
        asteroid.animate()
        android.track_time()
        DS.blit(island, (DISPLAY_WIDTH/2 - 300, DISPLAY_HEIGHT - 210))
        DS.blit(storm_cloud,(-40, -80))
        if android.crushed == True:
            current_scene = 5.01
        if android.fell == True:
            current_scene = 5.02
        if text(arial30b, "", (750, 10, 40, 40), (255, 255, 255), 200, True):
            current_scene = 8.1
        pygame.draw.line(DS, (100, 100, 100), (763, 20), (763, 40), 5)
        pygame.draw.line(DS, (100, 100, 100), (777, 20), (777, 40), 5)
        if android.time == 0:
            current_scene = 5.1
    elif current_scene == 5.1:
        if boxCol_still.y >= DISPLAY_HEIGHT - 50:
            current_scene = 9.1
            intro4_ran = False
        else:
            DS.blit(night_bg, (0, 0))
            android.constrain()
            android.move()
            DS.blit(island, (DISPLAY_WIDTH/2 - 300, DISPLAY_HEIGHT - 210))
            text(mono30b, "EARTH: MIRACULOUSLY, THE SPACESHIP SURVIVED", (DISPLAY_WIDTH/2 - 786/2, 10, 786, 35), (50, 255, 50), 10)
            text(mono30b, "AND THE GROUND BASE HAS BEEN CREATED.", (DISPLAY_WIDTH/2 - 675/2, 45, 675, 35), (50, 255, 50), 10)
            text(mono30b, "JUMP OFF THE RIGHT SIDE OF THE ISLAND", (DISPLAY_WIDTH/2 - 679/2, 80, 679, 35), (50, 255, 50), 10)
            text(mono30b, "ONTO THE BASE'S CATCHING PLATFORM", (DISPLAY_WIDTH/2 - 611/2, 115, 611, 35), (50, 255, 50), 10)
    elif current_scene == 9.1:
        if not intro4_ran:
            boxCol_still.x, boxCol_moving.x, boxCol_still.y, boxCol_moving.y = 50, 50, -100, -100
            intro4_ran = True
        if boxCol_still.x >= 600:
            android.died = True
            background_x1 = 0
            current_scene = 9.2
        else:
            DS.blit(intro4, (0, 0))
            pygame.draw.ellipse(DS, (40, 30, 20), (375, 514, 150, 60), 0)
            android.constrain()
            android.move()
            DS.blit(rocket_crashed, (-50, 330))
            pygame.draw.ellipse(DS, (40, 30, 20), (200, 514, 100, 60), 0)
            pygame.draw.rect(DS, (60, 60, 60), (0, 530, 90, 10), 0)
            pygame.draw.ellipse(DS, (60, 60, 60), (0, 510, 90, 40), 0)
            pygame.draw.rect(DS, (40, 30, 20), (0, 537, 800, 67), 0)
            pygame.draw.rect(DS, (150, 150, 150), (550, 150, 250, 400), 0)
            text(mono50b, "LAB", (625, 165, 0, 0), (50, 50, 50))
            text(mono30b, "EARTH: SCAVENGE THE PLANET FOR", (DISPLAY_WIDTH/2 - 554/2, 10, 554, 35), (50, 255, 50), 10)
            text(mono30b, "RESOURCES TO REPAIR THE SPACESHIP", (DISPLAY_WIDTH/2 - 607/2, 45, 607, 35), (50, 255, 50), 10)
            text(mono30b, "AND RETURN TO EARTH", (DISPLAY_WIDTH/2 - 357/2, 80, 357, 35), (50, 255, 50), 10)
    elif current_scene == 9.2:
        if android.died == True:
            enemies.clear()
            enemies.extend((Enemy(400, 0), Enemy(600, 1), Enemy(900, 0), Enemy(1100, 0), Enemy(1300, 1), Enemy(1600, 1), Enemy(1800, 0), Enemy(2100, 0), Enemy(2300, 1), Enemy(2600, 1), Enemy(2900, 1), Enemy(3200, 1), Enemy(3600, 1), Enemy(4000, 1), Enemy(4200, 0), Enemy(4400, 0), Enemy(4700, 1), Enemy(5000, 1), Enemy(5200, 0), Enemy(5500, 1), Enemy(5900, 0), Enemy(6300, 0), Enemy(6500, 1), Enemy(6800, 1), Enemy(7200, 1), Enemy(7500, 0), Enemy(7700, 1)))
            background_x1 = 0
            boxCol_still.x, boxCol_moving.x, boxCol_still.y, boxCol_moving.y = 50, 50, 500, 500
            android.scroll_x = 50
            android.crushed = False
            android.impaled = False
            android.died = False
        if android.scroll_x >= 8500:
            current_scene = 9.29
        else:
            DS.blit(bricks, (background_x1 + 800*0, 0))
            DS.blit(bricks, (background_x1 + 800*1, 0))
            DS.blit(bricks, (background_x1 + 800*2, 0))
            DS.blit(bricks, (background_x1 + 800*3, 0))
            DS.blit(bricks, (background_x1 + 800*4, 0))
            DS.blit(bricks, (background_x1 + 800*5, 0))
            DS.blit(bricks, (background_x1 + 800*6, 0))
            DS.blit(bricks, (background_x1 + 800*7, 0))
            DS.blit(bricks, (background_x1 + 800*8, 0))
            DS.blit(bricks, (background_x1 + 800*9, 0))
            DS.blit(bricks, (background_x1 + 800*10, 0))
            DS.blit(bricks, (background_x1 + 800*11, 0))
            DS.blit(bricks, (background_x1 + 800*12, 0))
            DS.blit(bricks, (background_x1 + 800*13, 0))
            DS.blit(bricks, (background_x1 + 800*14, 0))
            DS.blit(bricks, (background_x1 + 800*15, 0))
            DS.blit(bricks, (background_x1 + 800*16, 0))
            if boxCol_moving.x >= DISPLAY_WIDTH/2 and pygame.key.get_pressed()[pygame.K_RIGHT]:
                background_x1 -= android.speed
            elif boxCol_moving.x <= DISPLAY_WIDTH/2 and pygame.key.get_pressed()[pygame.K_LEFT] and android.scroll_x >= DISPLAY_WIDTH/2:
                background_x1 += android.speed
            android.constrain()
            android.move()
            for i in enemies:
                i.animate()
            pygame.draw.rect(DS, (80, 60, 40), (0, 0, 800, 150), 0)
            pygame.draw.rect(DS, (80, 60, 40), (0, 537, 800, 67), 0)
            DS.blit(direction, (background_x1 + 50, 0))
            android.track_distance()
            if android.crushed == True:
                current_scene = 9.21
            if android.impaled == True:
                current_scene = 9.22
            if text(arial30b, "", (750, 10, 40, 40), (255, 255, 255), 200, True):
                current_scene = 8.2
            pygame.draw.line(DS, (100, 100, 100), (763, 20), (763, 40), 5)
            pygame.draw.line(DS, (100, 100, 100), (777, 20), (777, 40), 5)
    elif current_scene == 9.29:
        DS.blit(bricks, (0, 0))
        android.constrain()
        android.move()
        DS.blit(box, (525, 340))
        pygame.draw.rect(DS, (80, 60, 40), (0, 0, 800, 150), 0)
        pygame.draw.rect(DS, (80, 60, 40), (0, 537, 800, 67), 0)
        pygame.draw.rect(DS, (80, 60, 40), (750, 150, 50, 387), 0)
        if boxCol_moving.x >= 455:
            text(mono30b, "THESE PARTS ARE EXACTLY WHAT WE NEED", (DISPLAY_WIDTH/2 - 663/2, 10, 663, 35), (50, 255, 50), 10)
            text(mono30b, "TO REPAIR THE SPACESHIP.", (DISPLAY_WIDTH/2 - 440/2, 45, 440, 35), (50, 255, 50), 10)
        else:
            text(mono30b, "ROBOT: THE RESOURCES WE NEED ARE HERE.", (DISPLAY_WIDTH/2 - 695/2, 10, 693, 35), (50, 255, 50), 10)
            text(mono30b, "OUR SEARCH IS OVER.", (DISPLAY_WIDTH/2 - 350/2, 45, 350, 35), (50, 255, 50), 10)
        if boxCol_moving.x >= 560:
            current_scene = 9.3
    elif current_scene == 9.3:    
        if not intro5_ran:
            boxCol_still.x, boxCol_moving.x, boxCol_still.y, boxCol_moving.y = 600, 600, 300, 300
            intro5_ran = True
        if (100 <= boxCol_moving.x <= 180) and boxCol_moving.y + android.jump_height >= 480:
            intro6_ran = False
            current_scene = 9.4
        else:
            DS.blit(intro4, (0, 0))
            pygame.draw.ellipse(DS, (40, 30, 20), (375, 514, 150, 60), 0)
            android.constrain()
            android.move()
            DS.blit(rocket_crashed, (-50, 330))
            pygame.draw.ellipse(DS, (40, 30, 20), (200, 514, 100, 60), 0)
            pygame.draw.rect(DS, (60, 60, 60), (0, 530, 90, 10), 0)
            pygame.draw.ellipse(DS, (60, 60, 60), (0, 510, 90, 40), 0)
            pygame.draw.rect(DS, (40, 30, 20), (0, 537, 800, 67), 0)
            pygame.draw.rect(DS, (150, 150, 150), (550, 150, 250, 400), 0)
            text(mono50b, "LAB", (625, 165, 0, 0), (50, 50, 50))
            text(mono30b, "ROBOT: WITH THESE NEW RESOURCES", (DISPLAY_WIDTH/2 - 570/2, 10, 570, 35), (50, 255, 50), 10)
            text(mono30b, "WE CAN REPAIR THIS SPACESHIP", (DISPLAY_WIDTH/2 - 517/2, 45, 517, 35), (50, 255, 50), 10)
            text(mono30b, "AND GO HOME", (DISPLAY_WIDTH/2 - 213/2, 80, 213, 35), (50, 255, 50), 10)
    elif current_scene == 9.4:
        if not intro6_ran:
            spaceship.y = 395
            spaceship_launch_height = 0.1
            intro6_ran = True
        if spaceship.y <= -2000:
            current_scene = 10
        else:
            DS.blit(intro4, (0, 0))
            pygame.draw.ellipse(DS, (40, 30, 20), (375, 514, 150, 60), 0)
            DS.blit(pygame.transform.rotate(standingStill, 90), (85, spaceship.y + 44))
            DS.blit(rocket_leaving, (-50, spaceship.y))
            pygame.draw.ellipse(DS, (40, 30, 20), (200, 514, 100, 60), 0)
            pygame.draw.rect(DS, (40, 30, 20), (0, 537, 800, 67), 0)
            pygame.draw.rect(DS, (150, 150, 150), (550, 150, 250, 400), 0)
            text(mono50b, "LAB", (625, 165, 0, 0), (50, 50, 50))
            spaceship.y -= spaceship_launch_height
            spaceship_launch_height += 0.05
    elif current_scene == 2:
        DS.fill((15, 15, 40))
        text(arial50b, "CONTROLS", (DISPLAY_WIDTH/2 - 245/2, 20, 245, 62), (255, 255, 255))
        text(arial30b, "Press LEFT or RIGHT to move", (DISPLAY_WIDTH/2 - 365/2, 350, 365, 42), (255, 255, 255))
        text(arial30b, "Press UP to jump", (DISPLAY_WIDTH/2 - 220/2, 400, 220, 42), (255, 255, 255))
        if text(arial20b, "BACK", (20, 20, 60, 28), (255, 255, 255), 100, True):
            current_scene = 1
    elif current_scene == 3:
        DS.fill((15, 15, 40))
        text(arial50b, "CREDITS", (DISPLAY_WIDTH/2 - 195/2, 20, 195, 62), (255, 255, 255))
        if text(arial20b, "BACK", (20, 20, 60, 28), (255, 255, 255), 100, True):
            current_scene = 1
        text(arial30b, "Game created and developed by", (DISPLAY_WIDTH/2 - 395/2, 175, 395, 41), (255, 255, 255))
        text(arial30b, "Akash Jagdeesh", (DISPLAY_WIDTH/2 - 210/2, 225, 210, 41), (255, 255, 255))
        text(arial30b, "Background image sources are printed", (DISPLAY_WIDTH/2 - 476/2, 350, 476, 41), (255, 255, 255))
        text(arial30b, "on the console", (DISPLAY_WIDTH/2 - 190/2, 400, 190, 41), (255, 255, 255))        
    elif current_scene == 5.01:
        DS.fill((40, 5, 5))
        text(arial50b, "YOU GOT CRUSHED!", (DISPLAY_WIDTH/2 - 430/2, 20, 430, 62), (255, 255, 255))
        DS.blit(crushed, (200, 230))
        DS.blit(crushed_island, (DISPLAY_WIDTH/2 - crushed_island.get_rect().w/2, 300))
        if text(arial30, "TRY AGAIN", (DISPLAY_WIDTH/2 - 145/2, 480, 145, 41), (255, 255, 255), 100, True):
            current_scene = 4.4
    elif current_scene == 5.02:
        DS.fill((40, 15, 15))
        text(arial50b, "YOU FELL OFF THE ISLAND!", (DISPLAY_WIDTH/2 - 576/2, 20, 576, 62), (255, 255, 255))
        DS.blit(fell, (DISPLAY_WIDTH/2 - fell.get_rect().w/2, 220))
        pygame.draw.line(DS, (255, 162, 0), (415, 230), (415, 120), 10)
        pygame.draw.circle(DS, (255, 162, 0), (416, 230), 5)
        pygame.draw.circle(DS, (255, 162, 0), (416, 120), 5)
        pygame.draw.line(DS, (255, 162, 0), (470, 330), (470, 220), 10)
        pygame.draw.circle(DS, (255, 162, 0), (471, 330), 5)
        pygame.draw.circle(DS, (255, 162, 0), (471, 220), 5)
        pygame.draw.line(DS, (255, 162, 0), (320, 220), (320, 100), 10)
        pygame.draw.circle(DS, (255, 162, 0), (321, 220), 5)
        pygame.draw.circle(DS, (255, 162, 0), (321, 100), 5)    
        if text(arial30, "TRY AGAIN", (DISPLAY_WIDTH/2 - 145/2, 480, 145, 41), (255, 255, 255), 100, True):
            current_scene = 4.4
    elif current_scene == 9.21:
        DS.fill((40, 5, 5))
        text(arial50b, "YOU GOT CRUSHED!", (DISPLAY_WIDTH/2 - 430/2, 20, 430, 62), (255, 255, 255))
        DS.blit(crushed1, (200, 230))
        pygame.draw.rect(DS, (80, 60, 40), (DISPLAY_WIDTH/2 - crushed_island.get_rect().w/2, 350, crushed_island.get_rect().w, crushed_island.get_rect().h - 50), 0)
        if text(arial30, "TRY AGAIN", (DISPLAY_WIDTH/2 - 145/2, 480, 145, 41), (255, 255, 255), 100, True):
            intro4_ran = False
            current_scene = 9.1
    elif current_scene == 9.22:
        DS.fill((40, 5, 5))
        text(arial50b, "YOU GOT IMPALED!", (DISPLAY_WIDTH/2 - 410/2, 20, 410, 62), (255, 255, 255))
        DS.blit(pygame.transform.flip(fell, True, True), (DISPLAY_WIDTH/2 - fell.get_rect().w/2, 100))
        DS.blit(impaled, (DISPLAY_WIDTH/2 - impaled.get_rect().w/2, 340))
        pygame.draw.rect(DS, (80, 60, 40), (DISPLAY_WIDTH/2 - crushed_island.get_rect().w/2, 400, crushed_island.get_rect().w, crushed_island.get_rect().h - 50), 0)
        if text(arial30, "TRY AGAIN", (DISPLAY_WIDTH/2 - 145/2, 480, 145, 41), (255, 255, 255), 100, True):
            intro4_ran = False
            current_scene = 9.1
    elif current_scene == 8.1:
        DS.fill((15, 15, 15))
        text(arial50b, "PAUSED", (DISPLAY_WIDTH/2 - 182/2, 20, 182, 62), (255, 255, 255))
        if text(arial30, "RESUME", (DISPLAY_WIDTH/2 - 120/2, 235, 120, 41), (255, 255, 255), 100, True):
            current_scene = 5
        if text(arial30, "RESTART LEVEL", (DISPLAY_WIDTH/2 - 210/2, 285, 210, 41), (255, 255, 255), 100, True):
            android.died = True
            current_scene = 4.4
        if text(arial30, "RETURN TO MENU", (DISPLAY_WIDTH/2 - 237/2, 335, 237, 41), (255, 255, 255), 100, True):
            android.died = True
            current_scene = 1
    elif current_scene == 8.2:
        DS.fill((15, 15, 15))
        text(arial50b, "PAUSED", (DISPLAY_WIDTH/2 - 182/2, 20, 182, 62), (255, 255, 255))
        if text(arial30, "RESUME", (DISPLAY_WIDTH/2 - 120/2, 235, 120, 41), (255, 255, 255), 100, True):
            current_scene = 9.2
        if text(arial30, "RESTART LEVEL", (DISPLAY_WIDTH/2 - 210/2, 285, 210, 41), (255, 255, 255), 100, True):
            intro4_ran = False
            current_scene = 9.1
        if text(arial30, "RETURN TO MENU", (DISPLAY_WIDTH/2 - 237/2, 335, 237, 41), (255, 255, 255), 100, True):
            android.died = True
            current_scene = 1
    elif current_scene == 10:
        DS.blit(sunset, (0, 0))
        DS.blit(pygame.transform.scale(rocket_leaving, (round(rocket_leaving.get_rect().w*1.5), round(rocket_leaving.get_rect().h*1.5))), (455, 305), (0, 0, rocket_leaving.get_rect().w, 225))
        DS.blit(pygame.transform.scale(outro_standing, (round(standingStill.get_rect().w*1.5), round(standingStill.get_rect().h*1.5))), (550, 455))
        text(mono30b, "EARTH: CONGRATULATIONS AND THANK YOU FOR", (DISPLAY_WIDTH/2 - 733/2, 10, 733, 35), (50, 255, 50), 10)
        text(mono30b, "SUCCESSFULLY RETURNING TO EARTH AND", (DISPLAY_WIDTH/2 - 643/2, 45, 643, 35), (50, 255, 50), 10)
        text(mono30b, "RESCUING THE MISSION", (DISPLAY_WIDTH/2 - 375/2, 80, 375, 35), (50, 255, 50), 10)
        if text(arial30, "MAIN MENU", (150, 480, 156, 41), (255, 255, 255), 100, True):
            android.died = True
            current_scene = 1
        if text(arial30, "CREDITS", (150+(156-122)/2, 530, 122, 41), (255, 255, 255), 100, True):
            current_scene = 3

print('IMAGE SOURCES:\n\nhttps://donkeysneakers.deviantart.com/art/Floating-island-png-690769014\n"Floating island png" by DonkeySneakers\npublished by DeviantArt\n\nhttp://www.freehdimages.in/wallpaper/desktop-images-of-the-moon-at-night-download/\n"Desktop images of the moon at night download"\npublished by FREEHDIMAGES\n\nhttps://spazcool.deviantart.com/art/Cloud-Black-640045467\n"Cloud Black" by spazcool\npublished by DeviantArt\n\nhttps://www.videvo.net/stock-video-footage/space/\n\nhttps://blogs.voanews.com/science-world/2012/04/18/like-sports-teams-solar-systems-may-swap-planets/\n"Like Sports Teams, Solar Systems May Swap Planets" by Rick Pantaleo\npublished by Voice of America\'s "Science World"\n\nhttps://www.getsurrey.co.uk/incoming/gallery/tim-peake-pictures-above-planet-11437767\n"Sun setting over the UK" by Tim Peake\npublished by Get Surrey\n\nhttp://www.zgjm-org.com/WDF-401741.html\n"Night Sky Stars Wallpapers"\npublished by Wallpapers groups presented by www.zgjm-org.com\n\nhttps://zagreb-dubrava.deviantart.com/art/Stone-Wall-Texture-316976890\n"Stone Wall Texture" by Zagreb-Dubrava\npublished by DeviantArt\n\nhttps://www.scirra.com/forum/free-graphic-2d-cartoon-spikes-amp-blades_t92173\n"FREE GRAPHIC: 2D Cartoon Spikes & Blades\npublished by Scirra\'s Construct 3\n\nhttps://www.flickr.com/photos/9572034@N07/1486757306/\n"Knowl Hill Sunset" by Graham Sivills\npublished by Flickr.com\n\nhttps://www.flickr.com/photos/photoshoproadmap/8640003215/in/photostream/\n"Wood crate texture" by Enrique Flouret\npublished by Flickr.com')

while True:
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
            pygame.quit()
            quit()

    scene()

    clock.tick(120)
    pygame.display.update()
