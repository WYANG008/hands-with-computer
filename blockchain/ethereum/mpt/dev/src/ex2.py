import sys
sys.path.append('src')
import trie, utils, rlp

#initialize trie from previous hash; add new entry with same key.
state = trie.Trie('triedb', '4a5b19d151e796482b08a1e020f1f7ef5ea7240c0171fd629598fee612892a7b'.decode('hex'))
print state.root_hash.encode('hex')
print state.root_node
print ''
state.update('\x01\x01\x02', rlp.encode(['hellothere']))
print state.root_hash.encode('hex')
print state.root_node
# we now have two tries, addressed in the database by their respective hashes, though they each have the same key