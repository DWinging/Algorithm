use std::io::{self, Read};

const MAX: usize = 100_000;

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();

    let mut iter = input.split_whitespace();

    let n: usize = iter.next().unwrap().parse().unwrap();

    let mut arr = vec![0i32; MAX + 1];
    let mut dp = vec![vec![0i64; 2]; MAX + 1];

    let mut max_val = 0;

    for _ in 0..n {
        let x: usize = iter.next().unwrap().parse().unwrap();
        arr[x] += 1;
        max_val = max_val.max(x);
    }

    for x in 1..=max_val {
        let val = x as i64 * arr[x] as i64;

        dp[x][0] = dp[x - 1][1] + val;
        dp[x][1] = if dp[x - 1][0] > dp[x - 1][1] {
            dp[x - 1][0]
        } else {
            dp[x - 1][1]
        };
    }

    println!("{}", dp[max_val][0].max(dp[max_val][1]));
}