# this is the game board
print(" --- --- ---")
print("|   |   |   |")
print(" --- --- --- ")
print("|   |   |   |")
print(" --- --- --- ")
print("|   |   |   |")
print(" --- --- --- ")
# blank board to start
board = [["","",""],["","",""],["","",""]]
#is the game over ??
gameover = False
#winners choices
winners = {0: "tie" , 1:"Player1" , 2 : "Player2"}
# checks winners
def check_winner ():

   # for i in range(0,3):
        if board[0] == ["x","x","x"] or board[1] == ["x","x","x"] or board [2] == ["x","x","x"]: # player1 horizontal wins
            return winners[1]
        if board[0] == ["o","o","o"] or board[1] == ["o","o","o"] or board [2] == ["o","o","o"]: # player2 horizontal wins
            return winners[2]
        elif board[0][0] == "x" and board[1][0] == "x" and board[2][0] == "x": # player1 vertical first column
            return winners[1]
        elif board[0][0] == "o" and board[1][0] == "o" and board[2][0] == "o": # player2 vertical first column
            return winners[2]
        elif board[0][1] == "x" and board[1][1] == "x" and board[2][1] == "x": # player1 vertical second column
            return winners[1]
        elif board[0][1] == "o" and board[1][1] == "o" and board[2][1] == "o'":  # player2 vertical second column
            return winners[2]
        elif board[0][2] == "x" and board[1][2] == "x" and board[2][2] == "x":  #player1  vertical third colunm
            return winners[1]
        elif board[0][2] == "o" and board[1][2] == "o" and board[2][2] == "o":  #player2  vertical third colunm
            return winners[2]
        elif board[0][0] == "x" and board[1][1] == "x" and board[2][2] == "x":  # player1 diagnol top bottom
            return winners[1]
        elif board[0][0] == "o" and board[1][1] == "o" and board[2][2] == "o":  # player2 diagnol top bottom
            return winners[2]
        elif board[0][2] == "x" and board[1][1] == "x" and board[2][0] == "x":  #player1  diagnol bottom top
            return winners[1]
        elif board[0][2] == "o" and board[1][1] == "o" and board[2][0] == "o":  #player2  diagnol bottom top
            return winners[2]
        else:
            return  winners[0]

winner = check_winner()



def player_one_turn():
    # ask user where to place marker
    player_one_move = input("Player One: type in row and column where you wanna place :")
    cord1 = player_one_move.split(",")
    # makes cord to ints for player one
    cord1x = int(cord1[0])
    cord1y = int(cord1[1])
    # places X on board
    if board[cord1x][cord1y] != "o" and board[cord1x][cord1y] != "x":
        board[cord1x][cord1y] = "x"
        # prints board
        print(board)
    elif board[cord1x][cord1y] == "o" or board[cord1x][cord1y] == "x":
        # message to let user know space is occupied
        print("Space is occupied Choose Again:")
        # call back player one turn
        player_one_turn()


def player_two_turn():
    # ask user where to place marker
    player_two_move = input("Player Two: type in row and column where you wanna place :")
    cord2 = player_two_move.split(",")
    # makes cord to ints for player one
    cord2x = int(cord2[0])
    cord2y = int(cord2[1])
    # places O on board
    if board[cord2x][cord2y] != "o" and board[cord2x][cord2y] != "x":
        board[cord2x][cord2y] = "o"
        # prints board
        print(board)

    elif board[cord2x][cord2y] == "o" or board[cord2x][cord2y] == "x":
        #message to let user know space is occupied
        #print("Space is occupied Choose Again:")
        # call back player twos turn
        player_two_turn()
# loop the actual Game



while check_winner() == "tie":

    if check_winner() != "Player1" or check_winner() != "Player2":
     player_one_turn()
    player_two_turn()
else:
    print("the winner is : " + check_winner())



#print("the winner is " + winner)