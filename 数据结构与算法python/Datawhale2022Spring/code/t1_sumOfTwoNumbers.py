class Solution:
    def twoSum(self, nums: list[int], target: int) -> list[int]:
        for i in range(len(nums) - 1):
            j = i + 1
            while j < len(nums):
                if nums[j] + nums[i] == target:
                    res = [j, i]
                    return res
                else:
                    j += 1
        return []
