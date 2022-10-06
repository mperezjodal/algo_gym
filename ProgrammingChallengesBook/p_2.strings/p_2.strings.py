def WheresWaldorf(mat, word):
    word = word.lower()
    for i in range(len(mat)):
        for j in range(len(mat[i])):
            if mat[i][j].lower() == word[0]:
                if check(mat, word, i, j):
                    return (i + 1, j + 1)
    return (-1, -1)

def check(mat, word, i, j):
    word_len = len(word)
    
    if i+word_len <= len(mat):
        if word == ''.join([mat[i+aux][j] for aux in range(word_len)]).lower():
            return True
    if i-word_len >= -1:
        if word == ''.join([mat[i-aux][j] for aux in range(word_len)]).lower():
            return True

    if j+word_len <= len(mat[0]):
        if word == ''.join([mat[i][j+aux] for aux in range(word_len)]).lower():
            return True
    if j-word_len >= -1:
        if word == ''.join([mat[i][j-aux] for aux in range(word_len)]).lower():
            return True

    if j+word_len <= len(mat[0]) and i+word_len <= len(mat):
        if word == ''.join([mat[i+aux][j+aux] for aux in range(word_len)]).lower():
            return True
    if j-word_len >= -1 and i-word_len >= -1:
        if word == ''.join([mat[i-aux][j-aux] for aux in range(word_len)]).lower():
            return True

    if j+word_len <= len(mat[0]) and i-word_len > -1:
        if word == ''.join([mat[i-aux][j+aux] for aux in range(word_len)]).lower():
            return True
    if j-word_len >= -1 and i+word_len <= len(mat):
        if word == ''.join([mat[i+aux][j-aux] for aux in range(word_len)]).lower():
            return True

    return False

   
mat = [["a","b","c","D","E","F","G","h","i","g","g"],
        ["h","E","b","k","W","a","l","D","o","r","k"],
        ["F","t","y","A","w","a","l","d","O","R","m"],
        ["F","t","s","i","m","r","L","q","s","r","c"],
        ["b","y","o","A","r","B","e","D","e","y","v"],
        ["K","l","c","b","q","w","i","k","o","n","k"],
        ["s","t","r","E","B","G","a","d","h","r","b"],
        ["y","U","i","q","l","x","e","n","B","j","f"]]

print(WheresWaldorf(mat, "Waldorf"))
print(WheresWaldorf(mat, "Bambi"))
print(WheresWaldorf(mat, "Betty"))
print(WheresWaldorf(mat, "Dagbert"))
print(WheresWaldorf(mat, "Marcelo"))

# abcDEFGhigg
# hEbkWalDork
# FtyAwaldORm
# FtsimrLqsrc
# byoArBeDeyv
# Klcbqwikonk
# strEBGadhrb
# yUiqlxenBjf