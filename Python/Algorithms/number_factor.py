def number_of_factors(n):
    count, k = 0, 1
    while k * k < n: 
        if n % k == 0:  
            count += 2
            k += 1
    #if k * k == n:
        #count += 1
    return count

k = int(input())
print(number_of_factors(k))
