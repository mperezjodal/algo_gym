
def bridge(people):
    flashlight_one_side = True
    one_side = people
    other_side = []
    strategy = []
    total_time = 0
    
    one_side.sort()

    while one_side:
        # print("one_side: {}, other_side: {}".format(one_side, other_side))
        if flashlight_one_side:
            move_index = 0
            if len(other_side) > 0 and len(one_side) > 2:
                if one_side[-3] < other_side[0]:
                    move_index = -1

            total_time += max(move_one(one_side, other_side, strategy, move_index), move_one(one_side, other_side, strategy, move_index))
        else:
            total_time += move_one(other_side, one_side, strategy, 0)

        one_side.sort()
        other_side.sort()
        flashlight_one_side = not flashlight_one_side

    print("Strategy: {}".format(strategy))
    return total_time


def move_one(from_side, to_side, strategy, index) -> int:
    to_move = int(from_side.pop(index))
    to_side.append(to_move)
    strategy.append(to_move)
    return to_move


assert(bridge([1,2,5,10]) == 17)