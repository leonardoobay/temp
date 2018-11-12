import random
randomNum = random.randint(1, 9)
counter = 1
guess = 0
print (randomNum)

while  int(guess) != randomNum:
 guess = input("Guess the number  between 1 and 9 : ")
 if int(guess) == randomNum:
    print("correct!!")
    print("It took you " + str(counter) + " tries")
 elif int(guess) < randomNum:
    print ("your guess was to low")
    counter  += 1
 elif int(guess) > randomNum:
    print("your guess was to high")
    counter += 1


