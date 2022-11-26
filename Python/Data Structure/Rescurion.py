def binary_search(data, target, low, high):
    if (low > high) :
        return False
    else:
        mid = (low + high) // 2
        if target == data[mid]:
            return True
        elif target < data[mid]:
            return binary_search(data, target, low, mid - 1)
        else:
            return binary_search(data, target, mid + 1,  high)

def linear_sum(Seq, n): #Summing the Elements of Sequence
    if n == 0:
        return 0
    else:
        return linear_sum(Seq, n - 1) + Seq[n - 1]

def Num_sum(n): #Sum by Pararmeter
    if n == 0:
        return 0
    else:
        return Num_sum(n-1) + n

def Reverse(Seq, start, stop): #Reverse Sequence
    if start < stop - 1:
        Seq[start], Seq[stop - 1] = Seq[stop - 1], Seq[start]
        Reverse(Seq, start + 1, stop -1)
