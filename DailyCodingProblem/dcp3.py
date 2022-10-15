"""
    Good morning! Here's your coding interview problem for today.

    This problem was asked by Google.

    Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.

    For example, given the following Node class

    class Node:
        def __init__(self, val, left=None, right=None):
            self.val = val
            self.left = left
            self.right = right

    The following test should pass:

    node = Node('root', Node('left', Node('left.left')), Node('right'))
    assert deserialize(serialize(node)).left.left.val == 'left.left'

"""

class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def serialize(node):
    if not node:
        return 'None'
    return node.val + ',' + serialize(node.left) + ',' + serialize(node.right)

def deserialize(s, node=None):
    if not s:
        return None
    if isinstance(s, str):
        s = s.split(',')

    if s[0] == 'None':
        return None
    
    node = Node(s[0])
    s.pop(0)
    node.left = deserialize(s)
    s.pop(0)
    node.right = deserialize(s)
    return node

node = Node('root', Node('left', Node('left.left')), Node('right'))
assert deserialize(serialize(node)).left.left.val == 'left.left'