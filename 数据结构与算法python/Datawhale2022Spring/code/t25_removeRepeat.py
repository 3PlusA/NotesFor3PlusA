def removeRepeat(nums: list):
    if(len(nums)==0):
        return 0
    res = 0
    for i in range(1,len(nums)):
        if nums[i] == nums[i-1]:
            res += 1
        else:
            nums[i-res] = nums[i] #与前面一个不重复，往前挪
    return len(nums)-res

test = [1,1,2]
print(removeRepeat(test))