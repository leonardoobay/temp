animals = ['cat' , 'dog' , 'goldfish', 'cow']
print(tuple(animals))
c, d, g, t = tuple(animals)
print('c = {}; d = {}; g = {}; t = {}'.format(c, d, g, t))
print((c, d, g, t) + tuple(animals))
print((c, d, g, t) + animals)