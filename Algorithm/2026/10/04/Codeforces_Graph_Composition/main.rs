use std::io::{self, Read};
use std::str::FromStr;
use std::fmt::Write;

fn next<T: FromStr>(iter: &mut std::str::SplitWhitespace) -> T {
    iter.next().unwrap().parse().ok().unwrap()
}

fn input_edge(
    iter: &mut std::str::SplitWhitespace,
    m: usize
) -> Vec<(usize, usize)> {
    let mut edges = Vec::with_capacity(m);

    for _ in 0..m {
        let u: usize = next(iter);
        let v: usize = next(iter);

        edges.push((u, v));
    }

    edges
}

fn find(p: usize, parents: &mut Vec<usize>) -> usize {
    if p == parents[p]  {
        p
    }
    else {
        let next = parents[p];
        let root = find(next, parents);
        parents[p] = root;
        root
    }
}

fn union(
    a: usize,
    b: usize,
    parents: &mut Vec<usize>
) {
    let p_a = find(a, parents);
    let p_b = find(b, parents);

    if p_a != p_b {
        parents[p_b] = p_a;
    }
}

fn count_parents(
    n: usize,
    parents: &[usize]
) -> i32 {
    let mut cnt: i32 = 0;

    for i in 1..=n {
        if parents[i] == i {
            cnt += 1;
        }
    }

    cnt
}

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();

    let mut iter = input.split_whitespace();

    let t: usize = next(&mut iter);
    let mut output = String::with_capacity(t * 20);
    
    for _ in 0..t {
        let n: usize = next(&mut iter);
        let m1: usize = next(&mut iter);
        let m2: usize = next(&mut iter);

        let f_edge = input_edge(&mut iter, m1);
        let g_edge = input_edge(&mut iter, m2);

        let mut g_parents: Vec<usize> = (0..=n).collect();
        
        for &(u, v) in &g_edge {
            union(u, v, &mut g_parents);
        }
        

        let mut f_parents: Vec<usize> = (0..=n).collect();
        let mut cnt:i32 = 0;
        for &(u, v) in &f_edge {
            let g_u = find(u, &mut g_parents);
            let g_v = find(v, &mut g_parents);

            if g_u != g_v {
                cnt += 1;
            } else {
                union(u, v, &mut f_parents);
            }
        }
        
        let f_p_cnt = count_parents(n, &f_parents);
        let g_p_cnt = count_parents(n, &g_parents);

        writeln!(output, "{}", cnt + f_p_cnt - g_p_cnt).unwrap();
    }
    print!("{output}");
}