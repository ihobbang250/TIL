bagSize = int(input())
itemNumber = int(input())
itemSize = list(map(int, input().split()))
itemProfit = list(map(int, input().split()))
itemSize.insert(0, 0)
itemProfit.insert(0, 0)


x = [0 for i in range(bagSize)]
global MaxProfit
MaxProfit = 0
global frac_max
frac_max = 0
perform = 0
k = {}

for j in range(i, itemNumber):
    perform = itemProfit[j] / itemSize[j]
    k[itemSize[j]] = perform
sorted_k = sorted(k.items(), key= lambda x: x[1])


def knapsack (i, size):
    global MaxProfit
    if i > itemNumber or size <= 0:
        print(x)
        return
    
    p = sum(itemProfit[j] for j in range(0, i) if x[j] == 1)
    s = sum(itemSize[j] for j in range(0, i) if x[j] == 1)
    Bag = frac_knapsack(i + 1, size - s)
    
    #Case x[i] = 1 
    if itemSize[i] <= size:
        Bag = frac_knapsack(i + 1, size - itemSize[i])
        if Bag == None:
            Bag = frac_knapsack(i, size - itemSize[i])
        if p + itemProfit[i] + Bag > MaxProfit:
            MaxProfit = p + itemProfit[i]
        x[i] = 1
        knapsack(i + 1, size - itemSize[i])

    #Case x[i] = 0
    Bag = frac_knapsack(i + 1, size)
    if p + Bag > MaxProfit:
        x[i] = 0
        knapsack(i + 1, size)           
            
def frac_knapsack (i, size):
    global frac_max
    
    if i > itemNumber or size <= 0:
        return None
    
    
    
        