def Map map = ["a":1, "b":2]

int sum_values(Map map) {
    int result = 0
    map.each { result += it.value }
    result
}

println(sum_values(map)) // => 3
println(sum_values("a": 1, "b": 3)) // => 4

def int r
r = sum_values "a": 1
println(r) // => 1
r = sum_values "a": 1, "b": 2
println(r) // => 3
