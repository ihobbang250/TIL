#sorting

def getmax_index(A, i): #Selection 정렬최대값 인덱스 리턴
    m , idx = A[0], 0
    for j in range(1, i + 1):
        if m <= A[j]:
            m, idx = A[j], j
    return idx
# stable selection