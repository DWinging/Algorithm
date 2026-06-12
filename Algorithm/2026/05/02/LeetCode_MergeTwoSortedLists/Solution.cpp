/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
        ListNode* dummyHead = new ListNode(0);
        ListNode* cur = dummyHead;

        while(list1 != nullptr && list2 != nullptr) {
            int val1 = list1 -> val;
            int val2 = list2 -> val;

            if(val1 <= val2) {
                cur -> next = list1;
                list1 = list1 -> next;
            }  else {
                cur -> next = list2;
                list2 = list2 -> next;
            }
            cur = cur -> next;
        }

        if(list1 != nullptr) {
            cur -> next = list1;
        }

        if(list2 != nullptr) {
            cur -> next = list2;
        }

        return dummyHead -> next;
    }
};