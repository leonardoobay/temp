
import random
# set random number from 1000 to 9999  because has to be four digits
randomNum = random.randint(1000, 9999)
# count represents the turn taken to guess answer
count = 1
# initial user guess is 0
userguess = 0
cow = 0 # guessed correct
bull = 0 # how many you guessed wrong doesnt show you the exact place
# randAnswer makes the random number meant to guess into a list of numbers
randAnswer = [int(randAnswer) for randAnswer in str(randomNum)]

'''
yourguess = [int(yourguess) for yourguess in str(userguess)]
randAnswer = [int(randAnswer) for randAnswer in str(randomNum)]
'''

# cant get bulls to display right
#welcome Message
print("Welcome to the Cows and Bulls Game!")
print (randomNum)
#userguess = input("Enter a number")
#yourguess = [int(yourguess) for yourguess in str(userguess)]

while  int(userguess) != randomNum:

 cow = 0  # guessed correct
 bull = 0  # guessed wrong
 userguess = input("Enter a number")
 yourguess = [int(yourguess) for yourguess in str(userguess)]
 if int(userguess) == randomNum:
    print("correct!!")
    print("It took you " + str(count) + " tries")
 elif  userguess == "exit":
   break
 else:

  for i in range(len(str(randomNum))):
     if randAnswer[i] == yourguess[i]:
         cow += 1
        # bull -= 1

# fix bulls part doesnt work when forur numbers are the same
     elif yourguess[i] in randAnswer:
       if bull <=0:
           bull = 0
       else:
        bull += 1


  print(str(cow) + " cows " + str(bull) + " bulls")
  print("Try agian!")







