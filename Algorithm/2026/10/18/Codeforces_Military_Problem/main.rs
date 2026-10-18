use std::io::{self, Read};
use std::str::FromStr;
use std::fmt::Write;

fn next<T: FromStr>(iter :&mut std::str::SplitWhitespace) -> T {
    iter.next().unwrap().parse().ok().unwrap()
}

fn create_tree(
    arr: &[Vec<usize>],
    tree: &mut [usize],
    start: &mut [usize],
    size: &mut [usize],
    node: usize,
    idx: &mut usize
) -> usize {
    start[node] = *idx;
    tree[*idx] = node;
    *idx += 1;

    for &child in &arr[node] {
        size[node] += create_tree(arr, tree, start, size, child, idx);
    }

    return size[node];
}

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();

    let mut iter = input.split_whitespace();

    let n:usize = next(&mut iter);
    let m:usize = next(&mut iter);

    let mut arr = vec![Vec::<usize>::new(); n + 1];

    for i in 2..=n {
        let parent: usize = next(&mut iter);
        arr[parent].push(i);
    }

    let mut tree = vec![0usize;n + 1];
    let mut start = vec![0usize;n + 1];
    let mut size = vec![1usize;n + 1];

    let mut idx = 0usize;
    create_tree(&arr, &mut tree, &mut start, &mut size, 1, &mut idx);

    let mut output = String::new();

    for _ in 0..m {
        let node: usize = next(&mut iter);
        let cnt: usize = next(&mut iter);
        
        if cnt > size[node] {
            writeln!(output, "-1").unwrap();
        } else {
            writeln!(output, "{}", tree[start[node] + cnt - 1]).unwrap();
        }
    }
    print!("{}", output);
}
