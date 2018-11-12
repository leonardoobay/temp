num = input("Input a number : ")
check = input("Input a second number : ")

if (int(num) % 4) == 0:
 print(num + " is  an multiple of four ")
elif (int(num) % 2) >= 1:
    print(num + " is  an odd number")
elif (int(num) % 2) == 0:
    print(num + " is  an even  number")

if (int(num) % int(check)) == 0:
 print(check + " divides evenly into  " + num)
elif (int(num) % 2) >= 1:
    print(check + " does not divide  evenly into  " + num)
