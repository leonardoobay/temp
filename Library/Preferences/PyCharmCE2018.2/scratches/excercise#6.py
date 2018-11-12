palinornot = input("Please input a String: ")

lenString = len(palinornot)

backward = ''
for i in range(lenString):
    backward += palinornot[lenString - 1 - i]



if palinornot == backward:
 print("This is a Palindrome")
elif palinornot != backward:
    print("This is not a Palindrome")

