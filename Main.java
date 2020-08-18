import java.util.*;

public class Main {
    public String PrintMinNumber(int [] numbers) {
        // 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
        // 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为 321323。

        // 先排序，再拼接
        // 自定义一个比较大小的函数，比较两个字符串s1, s2大小的时候，先将它们拼接起来
        // 比较 s1 + s2, 和 s2 + s1
        // 如果 s1 + s2 大，那说明 s2 应该放前面，所以按这个规则，s2 就应该排在 s1 前面。

        String str = "";
        for (int i = 0; i < numbers.length; i++){
            for (int j = i + 1; j < numbers.length; j++){
                int a = Integer.valueOf(numbers[i] + numbers[j]);
                int b = Integer.valueOf(numbers[j] + numbers[i]);
                if (a > b){
                    int t = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = t;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            str += String.valueOf(numbers[i]);
        }
        return str;
    }

    public int GetUglyNumber_Solution(int index) {
        // 把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。
        // 例如 6、8 都是丑数，但 14 不是，因为它包含质因子 7。
        // 习惯上我们把 1 当做是第一个丑数。
        // 求按从小到大的顺序的第 N 个丑数。

        if (index < 1) {
            return 0;
        }
        if (index == 1) {
            return 1;
        }
        List<Integer> list = new ArrayList<>();
        // 三个下标用于记录丑数的位置
        int i2 = 0, i3 = 0, i5 = 0;
        list.add(1);
        while(list.size() < index) {
            // 三个数都是可能的丑数，取最小的放进丑数数组里面
            int n2 = list.get(i2) * 2;
            int n3 = list.get(i3) * 3;
            int n5 = list.get(i5) * 5;
            int min = Math.min(n2, Math.min(n3, n5));
            list.add(min);
            if (min == n2) {
                i2++;
            }
            if (min == n3) {
                i3++;
            }
            if (min == n5) {
                i5++;
            }
        }
        return list.get(list.size() - 1);
    }

    public int FirstNotRepeatingChar(String str) {
        // 在一个字符串(0<=字符串长度 <= 10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
        // 如果没有则返回 -1（需要区分大小写）.（从 0 开始计数）

        if (str.length() == 0) {
            return -1;
        }
        Map<Character, Integer> map = new LinkedHashMap<>();
        // 按插入顺序（LinkedHashMap）排列而不是字典序（HashMap）
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], 1);
            } else {
                map.put(chars[i], map.get(chars[i]) + 1);
            }
            count++;
        }
        char key = '0';
        for (Map.Entry m : map.entrySet()) {
            if ((int)m.getValue() == 1) {
                key = (char)m.getKey();
                break;
            }
            count--;
        }
        if (count == 0) {
            // 说明没有只出现一次的字符串
            return -1;
        }
        int val = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == key) {
                val = i;
            }
        }
        return val;
    }

    public static int GetNumberOfK(int[] array , int k) {
        // 统计一个数字在升序数组中出现的次数
        // 注意：有可能 array 中所有数字都小于 k，因此循环条件不能写成
        // while (array[i] <= k), 会发生数组越界

        int i = 0;
        int count = 0;
        while (i < array.length) {
            if (array[i] == k) {
                count++;
            }
            if (array[i] > k) {
                break;
            }
            if (i < array.length) {
                i++;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        int ret = FirstNotRepeatingChar("google");
//        System.out.println(ret);

        int[] array = {1, 2, 3, 3, 3, 3};
        int k = 3;
        System.out.println(GetNumberOfK(array, k));
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 输入两个链表，找出它们的第一个公共结点。
        // 注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的

        // 通过 map 的特性实现
        ListNode cur1 = pHead1;
        ListNode cur2 = pHead2;
        Map<ListNode, Integer> map = new HashMap<>();
        while (cur1 != null) {
            map.put(cur1, 1);
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            if (map.containsKey(cur2)) {
                return cur2;
            }
            cur2 = cur2.next;
        }
        return null;
    }
}