use std::io::{self, Read};
use std::str::FromStr;
use std::fmt::Write;

const INF: usize = 200_000;

fn next<T: FromStr>(iter :&mut std::str::SplitWhitespace) -> T {
    iter.next().unwrap().parse().ok().unwrap()
}

fn dfs(
    dp: &mut Vec<[i32; 2]>,
    arr: &Vec<i32>,
    idx: usize,
    diff: i32,
    n: usize
) -> i32 {
    if idx >= n { return 0 }
    if dp[idx][diff as usize] > -1 { return dp[idx][diff as usize]}

    let mut val = dfs(dp, arr, idx + 1, diff ^ 1, n) + arr[idx] * diff;
    if idx + 1 < n {
        let val2 = dfs(dp, arr, idx + 2, diff ^ 1, n) + (arr[idx] + arr[idx + 1]) * diff;
        val = val.min(val2);
    }

    dp[idx][diff as usize] = val;
    val
}

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();

    let mut iter = input.split_whitespace();

    let t: usize = next(&mut iter);
    let mut output = String::with_capacity(t * 20);

    let mut dp = vec![[-1; 2]; INF];
    let mut arr = vec![0; INF];

    for _ in 0..t {
        let n: usize = next(&mut iter);

        for i in 0..n {
            arr[i] = next(&mut iter);
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        let res = dfs(&mut dp, &arr, 0, 1, n);
        
        writeln!(output, "{res}").unwrap();
    }

    print!("{output}");
}