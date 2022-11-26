def is_sorted(A):
    if len(A) < 2:
        return True
    for i in range(1, len(A)):
        if A[i - 1] > A[i]:
            return False
    return True

def insertion_sort(A):
    for k in range(1, len(A)):
        cur = A[k]
        j = k
        while j > 0 and A[j - 1] > cur:
            A[j] = A[j - 1]
            j -= 1
        A[j] = cur

def merge(S1, S2, S):
    i = j = 0
    while i+j < len(S):
        if j == len(S2) or (i < len(S1) and S[i] < S[j]):
            S[i+j] = S[i]
            i += 1
        else:
            S[i+j] = S[j]
            j += 1

def merge_sort(S):
    n = len(S)
    if n < 2:
        return
    mid = n // 2
    S1 = S[0 : mid]
    S2 = S[mid : n]
    merge_sort(S1)
    merge_sort(S2)
    merge(S1, S2, S)

def quick_sort(S):
    if len(S) < 2:
        return
    #divide
    pivot = S[0]
    L, E, G = [], [], []

    while len(S) > 0:
        x = S.pop()
        if x < pivot:
            L.append(x)
        elif x == pivot:
            E.append(x)
        else:
            G.append(x)

    #conquer
    quick_sort(L)
    quick_sort(G)

    while len(L) > 0:
        S.append(L.pop(0))
    while len(E) > 0:
        S.append(E.pop(0))
    while len(G) > 0:
        S.append(G.pop(0))