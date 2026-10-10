use std::io::{self, Read};
use std::str::FromStr;
use std::fmt::Write;

const INF: usize = 200_000;

fn next<T: FromStr>(iter: &mut std::str::SplitWhitespace) -> T {
    iter.next().unwrap().parse().ok().unwrap()
}

fn solve(
    dp: &mut Vec<[i32; 2]>,
    arr: &Vec<i32>,
    n: usize
) -> i32 {
    for i in (0..n).rev() {
        for diff in 0..2 {
            let next = if i + 1 < n {
                dp[i + 1][diff ^ 1]
            } else {
                0
            };

            let mut val = next + arr[i] * diff as i32;

            if i + 1 < n {
                let next2 = if i + 2 < n {
                    dp[i + 2][diff ^ 1]
                } else {
                    0
                };

                let val2 = next2 + (arr[i] + arr[i + 1]) * diff as i32;
                val = val.min(val2);
            }

            dp[i][diff] = val;
        }
    }

    dp[0][1]
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

        let res = solve(&mut dp, &arr, n);
        writeln!(output, "{res}").unwrap();
    }

    print!("{output}");
}