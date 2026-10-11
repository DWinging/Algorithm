use std::io::{self, Read};
use std::str::FromStr;
use std::fmt::Write;
 
const INF: usize = 1_000_001;
 
fn next<T: FromStr>(iter :&mut std::str::SplitWhitespace) -> T {
    iter.next().unwrap().parse().ok().unwrap()
}
 
fn solve(arr: &Vec<i32>) -> String {
    let mut result: Vec<usize> = Vec::new();
 
    let mut visited = vec![0; INF];
    let mut day: i32 = 1;
    let mut cnt: i32 = 0;
    let mut event = 0;
 
    for &i in arr {
        if i < 0 {
            let id:usize = (-i) as usize;
            if(visited[id] != day) {
                return String::from("-1");
            }
            visited[id] = -day;
            cnt -= 1;
        } else {
            if(visited[i as usize].abs() == day) {
                return String::from("-1");
            }
            visited[i as usize] = day;
            cnt += 1;
        }
        event += 1;
 
        if(cnt == 0) {
            day += 1;
            result.push(event);
            event = 0;
        }
    }
 
    if(cnt == 0) {
        let mut output = String::new();
        writeln!(output, "{}", result.len()).unwrap();
 
        for value in result {
            write!(output, "{} ", value).unwrap();
        }
 
        return output;
    } else {
        return String::from("-1");
    }
}
 
fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();
 
    let mut iter = input.split_whitespace();
 
    let n: usize = next(&mut iter);
    let arr: Vec<i32> = (0..n).map(|_| next(&mut iter)).collect();
 
    print!("{}", solve(&arr));
}