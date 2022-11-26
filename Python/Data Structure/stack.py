class Stack:
    def __init__(self):
        self.data = []  # initialize

    def size(self):
        return len(self.data)

    def is_empty(self):
        return self.size() == 0  # True if empty, False otherwise

    def push(self, elem):
        self.data.append(elem)

    def pop(self):
        if self.is_empty():
            return 'error'
        return self.data.pop()

    def top(self):
        if self.is_empty():
            return 'error'
        return self.data[-1]

def is_matched(expr):
    lefty = '({['
    righty = ')}]'
    S = Stack()
    for c in expr:
        if c in lefty:
            S.push(c)
        elif c in righty:
            if S.is_empty():
                return False
            if righty.index(c) != lefty.index(S.pop()):  #mismatched
                return False
    return S.is_empty()

infix = input()  #1*2+3*(4-5)*6
postfix = []  #12*345-*6*+
s = Stack()
precedence = {'+' :  0, '-': 0, '/': 1, '*': 1, '(': 2}
# Operator
for ch in infix:
    if ch in '+-/*(' :
        while not s.is_empty() and precedence[ch] <= precedence[s.top()] :
            if s.top() == '(' :
                break
            op = s.pop()
            postfix.append(op)

        s.push(ch)
    elif ch == ')' :
        while not s.is_empty() and s.top() != '(' :
            op = s.pop()
            postfix.append(op)
        s.pop()

    else:
        postfix.append(ch)

while not s.is_empty():
    op = s.pop()
    postfix.append(op)

postfix = "".join(postfix)
print(postfix)






