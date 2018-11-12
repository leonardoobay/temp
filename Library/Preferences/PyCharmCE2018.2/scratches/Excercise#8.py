win = False

while win == False:
 p1 = input("Player One : Please select Rock , Paper or Scissors")
 p2 = input("Player Two : Please select Rock , Paper or Scissors")
 if p1 == p2:
  print("Its a tie Try again")
 elif p1 != p2:
  if p1 == "rock" and p2 == "paper":
      print("player two  wins  paper beats rock!!")
      print("GameOver")
      rematch = input("Rematch? Y/N")
      if rematch == "n" or rematch == "N":
          win = True
      elif rematch == "y" or rematch == "Y":
          win = False
  elif p1 == "rock" and p2 == "scissor":
      print("player one  wins  rock beats scissor!!")
      print("GameOver")
      rematch = input("Rematch? Y/N")
      if rematch == "n" or rematch == "N":
          win = True
      elif rematch == "y" or rematch == "Y":
          win = False
  elif p1 == "paper" and p2 == "rock":
      print("player one  wins  paper beats rock!!")
      print("GameOver")
      rematch = input("Rematch? Y/N")
      if rematch == "n" or rematch == "N":
          win = True
      elif rematch == "y" or rematch == "Y":
          win = False
  elif p1 == "paper" and p2 == "scissor":
      print("player two  wins  scissor beats paper!!")
      print("GameOver")
      rematch = input("Rematch? Y/N")
      if rematch == "n" or rematch == "N":
          win = True
      elif rematch == "y" or rematch == "Y":
          win = False
  elif p1 == "Scissor" and p2 == "rock":
      print("player two  wins  rock beats scissor!!")
      print("GameOver")
      rematch = input("Rematch? Y/N")
      if rematch == "n" or rematch == "N":
          win = True
      elif rematch == "y" or rematch == "Y":
          win = False
  elif p1 == "Scissor" and p2 == "paper":
      print("player one  wins  scissor beats paper!!")
      rematch = input("Rematch? Y/N")
      print("GameOver")
      rematch = input("Rematch? Y/N")
      if rematch == "n" or rematch == "N":
          win = True
      elif rematch == "y" or rematch == "Y":
          win = False






