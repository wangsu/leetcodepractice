package io.wangsu;

import io.wangsu.domain.ListNode;
import io.wangsu.domain.TreeNode;
import io.wangsu.service.LeetCodeHardService;
import io.wangsu.service.LeetCodeMediumService;
import io.wangsu.service.LeetCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by SuW on 12/19/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MyApplication.class})
@IntegrationTest
public class MainTests {
    private static final Logger log = LoggerFactory.getLogger(MainTests.class);

    @Inject
    LeetCodeService leetCodeService;
    @Inject
    LeetCodeMediumService leetCodeMediumService;
    @Inject
    LeetCodeHardService leetCodeHardService;

    @Test
    public void generalTest(){
        //long i= 21473956002147395600l;
        //log.info(""+i);
        int x = 2147395600;
        int result = 0;
        for(int i=1;i<x;i++){
            long lower = (long)i*(long)i;
            long higher = (long)(i+1)*(long)(i+1);
            if(lower<=x&&higher>x){
                result = i;
                break;
            }
        }
        log.info("result is {}",result);
        log.info("result is {}",46340*46340);
        log.info("result is {}",2147395600);
        log.info("result is {}",46341*46341);
    }

    @Test
    public void test389(){
        String s = "ymbgaraibkfmvocpizdydugvalagaivdbfsfbepeyccqfepzvtpyxtbadkhmwmoswrcxnargtlswqemafandgkmydtimuzvjwxvlfwlhvkrgcsithaqlcvrihrwqkpjdhgfgreqoxzfvhjzojhghfwbvpfzectwwhexthbsndovxejsntmjihchaotbgcysfdaojkjldprwyrnischrgmtvjcorypvopfmegizfkvudubnejzfqffvgdoxohuinkyygbdzmshvyqyhsozwvlhevfepdvafgkqpkmcsikfyxczcovrmwqxxbnhfzcjjcpgzjjfateajnnvlbwhyppdleahgaypxidkpwmfqwqyofwdqgxhjaxvyrzupfwesmxbjszolgwqvfiozofncbohduqgiswuiyddmwlwubetyaummenkdfptjczxemryuotrrymrfdxtrebpbjtpnuhsbnovhectpjhfhahbqrfbyxggobsweefcwxpqsspyssrmdhuelkkvyjxswjwofngpwfxvknkjviiavorwyfzlnktmfwxkvwkrwdcxjfzikdyswsuxegmhtnxjraqrdchaauazfhtklxsksbhwgjphgbasfnlwqwukprgvihntsyymdrfovaszjywuqygpvjtvlsvvqbvzsmgweiayhlubnbsitvfxawhfmfiatxvqrcwjshvovxknnxnyyfexqycrlyksderlqarqhkxyaqwlwoqcribumrqjtelhwdvaiysgjlvksrfvjlcaiwrirtkkxbwgicyhvakxgdjwnwmubkiazdjkfmotglclqndqjxethoutvjchjbkoasnnfbgrnycucfpeovruguzumgmgddqwjgdvaujhyqsqtoexmnfuluaqbxoofvotvfoiexbnprrxptchmlctzgqtkivsilwgwgvpidpvasurraqfkcmxhdapjrlrnkbklwkrvoaziznlpor";
        String t = "qhxepbshlrhoecdaodgpousbzfcqjxulatciapuftffahhlmxbufgjuxstfjvljybfxnenlacmjqoymvamphpxnolwijwcecgwbcjhgdybfffwoygikvoecdggplfohemfypxfsvdrseyhmvkoovxhdvoavsqqbrsqrkqhbtmgwaurgisloqjixfwfvwtszcxwktkwesaxsmhsvlitegrlzkvfqoiiwxbzskzoewbkxtphapavbyvhzvgrrfriddnsrftfowhdanvhjvurhljmpxvpddxmzfgwwpkjrfgqptrmumoemhfpojnxzwlrxkcafvbhlwrapubhveattfifsmiounhqusvhywnxhwrgamgnesxmzliyzisqrwvkiyderyotxhwspqrrkeczjysfujvovsfcfouykcqyjoobfdgnlswfzjmyucaxuaslzwfnetekymrwbvponiaojdqnbmboldvvitamntwnyaeppjaohwkrisrlrgwcjqqgxeqerjrbapfzurcwxhcwzugcgnirkkrxdthtbmdqgvqxilllrsbwjhwqszrjtzyetwubdrlyakzxcveufvhqugyawvkivwonvmrgnchkzdysngqdibhkyboyftxcvvjoggecjsajbuqkjjxfvynrjsnvtfvgpgveycxidhhfauvjovmnbqgoxsafknluyimkczykwdgvqwlvvgdmufxdypwnajkncoynqticfetcdafvtqszuwfmrdggifokwmkgzuxnhncmnsstffqpqbplypapctctfhqpihavligbrutxmmygiyaklqtakdidvnvrjfteazeqmbgklrgrorudayokxptswwkcircwuhcavhdparjfkjypkyxhbgwxbkvpvrtzjaetahmxevmkhdfyidhrdeejapfbafwmdqjqszwnwzgclitdhlnkaiyldwkwwzvhyorgbysyjbxsspnjdewjxbhpsvj";
        log.info("result {}",leetCodeService.findTheDifference(s,t));
    }

    @Test
    public void test448(){
        int[] input = new int[]{4,3,2,7,8,2,3,1};
        log.info("result {}",leetCodeService.findDisappearedNumbers(input));
    }

    @Test
    public void test463(){
        int[][] grid =new int[][] {
                new int[] { 0, 1, 0, 0 },
                new int[] { 1, 1, 1, 0},
                new int[] { 0, 1, 0, 0},
                new int[] { 1, 1, 0, 0}
        };

        log.info("result {}",leetCodeService.islandPerimeter(grid));

    }

    @Test
    public void test461(){
        log.info("result {}",leetCodeService.hammingDistance(211,4));
        log.info("result {}",leetCodeService.hammingDistance(1,4));
    }

    @Test
    public void test14(){
        String[] strs = {"aa","a"};
        log.info("result {}",leetCodeService.longestCommonPrefix(strs));
        String[] strs2 = {"a","a"};
        log.info("result {}",leetCodeService.longestCommonPrefix(strs2));
    }

    @Test
    public void test21(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode result = leetCodeService.mergeTwoLists(n1,n2);
        log.info("result {}",result.val);
        log.info("result {}",result.next.val);
    }

    @Test
    public void test4(){
        int[] nums1 = {0,};
        int[] nums2 = {2,4,5};
        log.info("result {}",leetCodeHardService.findMedianSortedArrays(nums1,nums2));
    }

    @Test
    public void test6(){
        log.info("result {}",leetCodeMediumService.convert("PAYPALISHIRING",3));
        log.info("result {}",leetCodeMediumService.convert("ABCD",2));
    }

    @Test
    public void test16(){
        //log.info("result {}",leetCodeMediumService.threeSumClosest(new int[]{0,2,1,-3},1));
        log.info("result {}",leetCodeMediumService.threeSumClosest(new int[]{1,2,4,8,16,32,64,128},82));
    }

    @Test
    public void test17(){
        //log.info("result {}",leetCodeMediumService.threeSumClosest(new int[]{0,2,1,-3},1));
        log.info("result {}",leetCodeMediumService.letterCombinations("22"));
    }

    @Test
    public void test18(){
        //log.info("result {}",leetCodeMediumService.threeSumClosest(new int[]{0,2,1,-3},1));
        log.info("result {}",leetCodeMediumService.fourSum(new int[]{0,0,0,0},0));
    }

    @Test
    public void test31(){
        int[] a = new int[]{1,3,2};
        leetCodeMediumService.nextPermutation(a);
        log.info("result {}",a);
    }

    @Test
    public void test54(){
        int[][] grid =new int[][] {
                new int[] { 7 },
                new int[] { 9},
                new int[] { 6}
        };

        log.info("result {}",leetCodeMediumService.spiralOrder(grid));
    }

    @Test
    public void test94(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        log.info("result {}",leetCodeMediumService.inorderTraversal(root));
    }

    @Test
    public void test95(){
        //log.info("result {}",leetCodeMediumService.generateTrees(3));
        log.info("result {}",leetCodeMediumService.generateTrees(0));
    }

    @Test
    public void test147(){
        ListNode two = new ListNode(2);
        ListNode one = new ListNode(1);
        two.next = one;
//        log.info("result {}",leetCodeMediumService.insertionSortList(two).val);
        log.info("result {}",leetCodeMediumService.insertionSortList(one).val);
    }
}
