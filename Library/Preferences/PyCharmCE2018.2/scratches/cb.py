def bullsandcows (secret:list , guess:list):
    bulls = 0
    cows =0
    unmatched ={}
    for (s,g) in zip(secret,guess):
        if s==g:
            bulls ++1
    else:
        if unmatched.setdefault(s,0) >0
            cows +=1
            unmatched [s] -=1
        else:
        if: unmatched[s].