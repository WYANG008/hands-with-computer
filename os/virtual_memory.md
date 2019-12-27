## Virtual Memory

protection & privacy

demand paging: provides the ability to run programs larger than main memory

the price of VM is address translation on eachmemory reference


machine language address -> ISA -> virtual address -> address mapping -> physical memory (DRAM)

SEGMENTATION ADDRESS TRANSLATION
each program's data is allocated in a contguous segment of physical memory
physical address = virtual address + segment base

Base Register and Bound Register
Base and bound registers should not be accessed by user programs (only accessible in supervisor mode)

the problem is memory fragmentation
each process has fixed memory size
as processes starts and end, storage is fragmented. therefore, at some point segments have to be moved around to compact the free space.


PAGED MEMORY SYSTEMS
divide physical memory in fixed-size blocks called pages. typical page size 4kB

Interpret each virtual address as a pair <virtual page number, offset>
Each process has a page table
page table has an entry fo reach process page

DEMAND PAGING
useing main memory as a cache of disk
all the pages of the processes may not fit in main memory. Therefore, DRAM is backed up by swap space on disk.


ref 
https://www.youtube.com/watch?v=W7Scg6LfZhY&t=93s