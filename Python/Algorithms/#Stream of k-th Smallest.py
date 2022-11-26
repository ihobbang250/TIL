#Stream of k-th Smallest
import heapq

def findKth(arr) :
    # A[0]~A[n]까지 원소를 순차적 순회하면서 그 중 k-th로 작은수를 바로 리스트에 저장한다.
    # m[k]값 A[0]~A[k] 중 (k//3+1)-th 값 -> m[k]list 원소3개당 th가 증가한다.
    # ex) k=0, 1, 2 -> 1th 값, k=3, 4, 5 -> 2th 값
    # 기본적으로 heapq 모듈을 이용하여 Min_heap 구조를 이용한다.
    # Min_heap 구조 : 이진트리 구조, 자식노드 >= 부모노드
    # 순차적 저장용도 : Plus_Min_Heap
    # -> 배열을 힙구조로 순차적으로 저장한다
    # k-th 값 추출용도 : Minus_Min_Heap
    # -> Min_heap 구조 가장 작은값 루트노드 
    # -> 노드에 -(minus)를 붙여 k-th값 루트노드로 설정 및 추출

    result = [] # m[k]값 저장리스트
    M_M_H = [] # Minus_Min_Heap
    P_M_H = [] # Plus_Min_Heap
    th = 0 # initailize th

    for i in arr: # 배열 n번 순회 O(n) = n
        if len(result) % 3 == 0: # 원소3개당 번째 변환에 대한 처리(3의 배수시 th 변환), Default 1-th
            th += 1 # -번째 변환시 k값 증가 ex) 1-th -> 2-th
        # Minus_Min_Heap 비어있거나, Minus_Min_Heap의 루트노드보다 i값이 작거나 같다면, -i 삽입
        if not M_M_H or -(M_M_H[0]) >= i:
            heapq.heappush(M_M_H, -i) #힙모듈이용 -> 힙구조로 삽입
        #그 외의 경우, Plus_min_heap에 i 삽입
        else:
            heapq.heappush(P_M_H, i)
    
        # Plus_Min_Heap 루트노드를 k-th 값 설정, Minus_Min_Heap 크기 th개 유지
        if len(M_M_H) > th:
            heapq.heappush(P_M_H, -heapq.heappop(M_M_H))
        elif len(M_M_H) < th and P_M_H:
            heapq.heappush(M_M_H, -heapq.heappop(P_M_H))

        # K-th 값 추출
        result.append(-(M_M_H[0]) if len(M_M_H) >= th else -1)

        # 이진트리 heap 생성 O(n) = logn

    # 배열 n번 순회 + heap 생성 -> O(n) = n * logn = O(n) = nlogn

    return sum(result) # 이미 리스트가 다 구해져있으므로 -> O(1)
    # 총 시간복잡도 O(n) = nlogn


list_A = [int(x) for x in input().split()] #list A 입력받음
print(findKth(list_A))

    
    

