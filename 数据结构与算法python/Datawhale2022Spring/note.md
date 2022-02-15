# Leetcode训练营笔记(DataWhale开源社区)

[TOC]

## 1.线性结构

### 1.1数组

#### 1.1.1课堂内容

**a.一二三维数组公式：**
$$
一维数组：Loc(a[i])=Loc(a[0])+i*c\\
二维数组：Loc(a[i,j])=Loc(a[0][0])+(i*n+j)*c\\
三维数组：Loc(a[i,j,k])=Loc(a[0][0][0])+(i*n*l+j*l+k)*c
$$

#### 1.1.2课堂例题

##### a.T1两数之和

**题目：**

> 给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** *`target`* 的那 **两个** 整数，并返回它们的数组下标。
>
> 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
>
> 你可以按任意顺序返回答案。
>
>  
>
> **示例 1：**
>
> ```
> 输入：nums = [2,7,11,15], target = 9
> 输出：[0,1]
> 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
> ```
>
> **示例 2：**
>
> ```
> 输入：nums = [3,2,4], target = 6
> 输出：[1,2]
> ```
>
> **示例 3：**
>
> ```
> 输入：nums = [3,3], target = 6
> 输出：[0,1]
> ```
>
>  
>
> **提示：**
>
> - `2 <= nums.length <= 104`
> - `-109 <= nums[i] <= 109`
> - `-109 <= target <= 109`
> - **只会存在一个有效答案**
>
> **进阶：**你可以想出一个时间复杂度小于 `O(n2)` 的算法吗？



**解：**

暴力

> ```
> class Solution:
>     def twoSum(self, nums: list[int], target: int) -> list[int]:
>         for i in range(len(nums) - 1):
>             j = i + 1
>             while j < len(nums):
>                 if nums[j] + nums[i] == target:
>                     res = [j, i]
>                     return res
>                 else:
>                     j += 1
>         return []
> ```

##### b.T15最接近三数之和

**题目：**

> 给你一个长度为 `n` 的整数数组 `nums` 和 一个目标值 `target`。请你从 `nums` 中选出三个整数，使它们的和与 `target` 最接近。
>
> 返回这三个数的和。
>
> 假定每组输入只存在恰好一个解。
>
>  
>
> **示例 1：**
>
> ```
> 输入：nums = [-1,2,1,-4], target = 1
> 输出：2
> 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
> ```
>
> **示例 2：**
>
> ```
> 输入：nums = [0,0,0], target = 1
> 输出：0
> ```
>
>  
>
> **提示：**
>
> - `3 <= nums.length <= 1000`
> - `-1000 <= nums[i] <= 1000`
> - `-104 <= target <= 104`

**解：**

O($n^3$)解法：

```python
嵌套三层循环，寻找和的绝对值最接近的数
```

O($n^2$)解法：

```python
def threeSumClosest(self, nums: List[int], target: int) -> int:
        nums.sort()
        result = nums[0] + nums[1] + nums[2] #用result记录最接近的值
        for i in range(len(nums)-2):
            start = i + 1
            end = len(nums) - 1
            while(start<end):
                summ = nums[i] + nums[start] + nums[end] #用summ记录一次循环的总和
                if(abs(target-summ)<abs(target-result)): #如果summ更接近target，则将result更新为新的summ
                    result = summ
                if(summ>target): #summ大了就将最后一个元素往前移，反之将中间的元素往后移
            	    end-=1
                elif(summ<target): 
                    start+=1
                else: #result与target相等，直接返回result
                    return result
        return result
```



#### 1.1.3课后题

##### a.T26移除元素

**题目**：

> 给你一个数组 `nums` 和一个值 `val`，你需要 **[原地](https://baike.baidu.com/item/原地算法?spm=5176.15228502.0.0.6cfe79bfJNFQz1)** 移除所有数值等于 `val` 的元素，并返回移除后数组的新长度。
>
> 不要使用额外的数组空间，你必须仅使用 `O(1)` 额外空间并 **[原地 ](https://baike.baidu.com/item/原地算法)修改输入数组**。
>
> 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
>
>  
>
> **说明:**
>
> 为什么返回数值是整数，但输出的答案是数组呢?
>
> 请注意，输入数组是以**「引用」**方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
>
> 你可以想象内部操作如下:
>
> ```
> // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
> int len = removeElement(nums, val);
> 
> // 在函数里修改输入数组对于调用者是可见的。
> // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
> for (int i = 0; i < len; i++) {
>     print(nums[i]);
> }
> ```
>
>  
>
> **示例 1：**
>
> ```
> 输入：nums = [3,2,2,3], val = 3
> 输出：2, nums = [2,2]
> 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
> ```
>
> **示例 2：**
>
> ```
> 输入：nums = [0,1,2,2,3,0,4,2], val = 2
> 输出：5, nums = [0,1,4,0,3]
> 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
> ```
>
>  
>
> **提示：**
>
> - `0 <= nums.length <= 100`
> - `0 <= nums[i] <= 50`
> - `0 <= val <= 100`



**解：**

前后双指针解决，删除方式采用删除一个就往前移以为而不是前后交换

```python
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
```



##### b.T25删除有序数组中的重复项

>给你一个有序数组 `nums` ，请你**[ 原地](http://baike.baidu.com/item/原地算法?spm=5176.15228502.0.0.169e79bfw0y7Rb)** 删除重复出现的元素，使每个元素 **只出现一次** ，返回删除后数组的新长度。
>
>不要使用额外的数组空间，你必须在 **[原地 ](https://baike.baidu.com/item/原地算法)修改输入数组** 并在使用 O(1) 额外空间的条件下完成。
>
> 
>
>**说明:**
>
>为什么返回数值是整数，但输出的答案是数组呢?
>
>请注意，输入数组是以**「引用」**方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
>
>你可以想象内部操作如下:
>
>```
>// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
>int len = removeDuplicates(nums);
>
>// 在函数里修改输入数组对于调用者是可见的。
>// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
>for (int i = 0; i < len; i++) {
>    print(nums[i]);
>}
>```
>
> 
>
>**示例 1：**
>
>```
>输入：nums = [1,1,2]
>输出：2, nums = [1,2]
>解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
>```
>
>**示例 2：**
>
>```
>输入：nums = [0,0,1,1,1,2,2,3,3,4]
>输出：5, nums = [0,1,2,3,4]
>解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
>```
>
> 
>
>**提示：**
>
>- `0 <= nums.length <= 3 * 104`
>- `-104 <= nums[i] <= 104`
>- `nums` 已按升序排列



**解：**

一个指针遍历，将不重复的提到前面，逐渐组成不重复序列

```python
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
```



##### c.T14三数之和

**题目：**

> 给你一个包含 `n` 个整数的数组 `nums`，判断 `nums` 中是否存在三个元素 *a，b，c ，*使得 *a + b + c =* 0 ？请你找出所有和为 `0` 且不重复的三元组。
>
> **注意：**答案中不可以包含重复的三元组。
>
>  
>
> **示例 1：**
>
> ```
> 输入：nums = [-1,0,1,2,-1,-4]
> 输出：[[-1,-1,2],[-1,0,1]]
> ```
>
> **示例 2：**
>
> ```
> 输入：nums = []
> 输出：[]
> ```
>
> **示例 3：**
>
> ```
> 输入：nums = [0]
> 输出：[]
> ```
>
>  
>
> **提示：**
>
> - `0 <= nums.length <= 3000`
> - `-105 <= nums[i] <= 105



**解：**

三指针操作

```python
class Solution:
    def threeSum(self, nums: list[int]) -> list[list[int]]:
        nums.sort()
        result = []
        for i in range(len(nums)):
            if nums[i] > 0:
                return result
            if i > 0 and nums[i - 1] == nums[i]:  # 对元素a做去重
                continue

            left = i + 1  # 左右双指针
            right = len(nums) - 1

            while left < right:
                if nums[i] + nums[left] + nums[right] > 0:  # 大了right左移
                    right -= 1
                elif nums[i] + nums[left] + nums[right] < 0:  # 小了left右移
                    left += 1
                else:
                    temp = [nums[i], nums[left], nums[right]]
                    result.append(temp)
                    while left < right and nums[right] == nums[right - 1]:  # 对元素c进行去重
                        right -= 1
                    while left < right and nums[left] == nums[left + 1]:  # 对元素b进行去重
                        left += 1
                    right -= 1
                    left += 1
        return result
```

