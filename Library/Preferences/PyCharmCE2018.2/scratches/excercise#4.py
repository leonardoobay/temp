num = input("Input a number")
x = range(2, 1+ int(num))

for elem in x:
 if int(num) % elem == 0:
  print (elem)
