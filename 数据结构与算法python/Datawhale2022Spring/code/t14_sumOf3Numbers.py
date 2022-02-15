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


nums = [1, 3, 2, 7, -1, 0, 0, 0, -2]
print(Solution().threeSum(nums))
