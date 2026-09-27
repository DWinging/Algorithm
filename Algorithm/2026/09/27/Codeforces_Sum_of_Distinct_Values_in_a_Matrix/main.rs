use std::fmt::Write;
use std::io::{self, Read};
use std::str::FromStr;

fn next<T: FromStr>(iter: &mut std::str::SplitWhitespace) -> T {
    iter.next().unwrap().parse().ok().unwrap()
}

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();

    let mut iter = input.split_whitespace();

    let t: usize = next(&mut iter);
    let mut output = String::with_capacity(t * 20);

    for _ in 0..t {
        let n: usize = next(&mut iter);
        let m: usize = next(&mut iter);
        let x_len: usize = next(&mut iter);
        let y_len: usize = next(&mut iter);

        let arr_a: Vec<i64> = (0..x_len)
            .map(|_| next(&mut iter))
            .collect();

        let arr_b: Vec<i64> = (0..y_len)
            .map(|_| next(&mut iter))
            .collect();
        
        let mut x = x_len as isize - 1;
        let mut y = y_len as isize - 1;

        let mut remain_a = n;
        let mut remain_b = m;
        let mut remain_total = n + m - 1;

        let mut res = 0_i64;

        while remain_total > 0 && (x >= 0 || y >= 0) {
            if x >= 0 && y >= 0 {
                let a = arr_a[x as usize];
                let b = arr_b[y as usize];

                if a == b {
                    res += a;
                    remain_total -= 1;
                    x -= 1;
                    y -= 1;
                } else if a > b {
                    if remain_a > 0 {
                        res += a;
                        remain_a -= 1;
                        remain_total -= 1;
                    }
                    x -= 1;
                } else {
                    if remain_b > 0 {
                        res += b;
                        remain_b -= 1;
                        remain_total -= 1;
                    }
                    y -= 1;
                }
                
            } else if x >= 0 {
                if remain_a > 0 {
                    res += arr_a[x as usize];
                    remain_a -= 1;
                    remain_total -= 1;
                }
                x -= 1;
            }
            else {
                if remain_b > 0 {
                    res += arr_b[y as usize];
                    remain_b -= 1;
                    remain_total -= 1;
                }
                y -= 1;            
            }
        }

        writeln!(output, "{res}").unwrap();
    }

    print!("{output}");
}