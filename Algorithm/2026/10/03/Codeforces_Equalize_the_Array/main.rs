use std::io::{self, Read};
use std::collections::HashMap;

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();

    let mut iter = input.split_whitespace();

    let t: usize = iter.next().unwrap().parse().unwrap();

    for _ in 0..t {
        let n: usize = iter.next().unwrap().parse().unwrap();

        let mut map: HashMap<i32, i32> = HashMap::new();

        for _ in 0..n {
            let x: i32 = iter.next().unwrap().parse().unwrap();
            *map.entry(x).or_insert(0) += 1;
        }

        let mut arr: Vec<i32> = map.values().copied().collect();

        arr.sort_unstable();

        let mut res : i32 = n as i32 - arr[0] * arr.len() as i32;
        let mut s : usize = 0;

        for i in 0..arr.len() {
            if arr[s] != arr[i] {
                s = i;
                let remain : i32 = arr[s] * (arr.len() - s) as i32;
                let temp = n as i32 - remain;

                if temp < res {
                    res = temp;
                } 
            }
        }
        
        println!("{}", res);
    }
}