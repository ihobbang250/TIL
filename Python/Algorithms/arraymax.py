def quick_select(array, k):
    p = array[0] #pivot
    s = m = l = []
    for i in array:
        if i < p: s.append(i)
        elif i > p: l.append(i)
        else: m.append(i)
    
    if len(s) >= k:
        return quick_select(s, k)
    elif len(s) + len(m) < k:
        return quick_select(l, k - len(s) - len(m))
    else:
        return p

quick_select([1, 3, 2, 5] , 1)
