def removeElement(nums: list, val: int):
    start = 0 #双指针
    end = len(nums) - 1
    res = len(nums)
    while start <= end: #start与end重合时停止
        if nums[start] == val:
            temp = start #temp用于删除元素时临时指针
            res -= 1
            while temp != end: #temp与end重合时停止删除
                nums[temp] = nums[temp+1]
                temp+=1
            end -= 1
        if(nums[start] == val): #更新后元素仍为val，则重复循环
            continue
        else:
            start += 1
    return res

numsTest = [0,1,2,2,3,0,4,2]
print(removeElement(numsTest, 2))