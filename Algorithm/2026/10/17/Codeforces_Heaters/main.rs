use std::io::{self, Read};
use std::str::FromStr;

fn next<T: FromStr>(iter: &mut std::str::SplitWhitespace) -> T {
    iter.next().unwrap().parse().ok().unwrap()
}

fn find_idx(arr: &[i32], left: usize, right: usize) -> Option<usize> {
    let mut idx = None;

    for i in left..right {
        if arr[i] == 1 {
            idx = Some(i);
        }
    }

    idx
}

fn solve(arr: &[i32], n: usize, r: usize) -> i32 {
    let mut idx = match find_idx(arr, 0, n.min(r)) {
        Some(i) => i,
        None => return -1,
    };

    let mut point = idx + r;
    let mut cnt = 1;

    while point < n {
        idx = match find_idx(
            arr,
            point.saturating_sub(r - 1),
            n.min(point + r),
        ) {
            Some(i) => i,
            None => return -1,
        };

        point = idx + r;
        cnt += 1;
    }

    cnt
}

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();

    let mut iter = input.split_whitespace();

    let n: usize = next(&mut iter);
    let r: usize = next(&mut iter);

    let arr: Vec<i32> = (0..n).map(|_| next(&mut iter)).collect();

    print!("{}", solve(&arr, n, r));
}