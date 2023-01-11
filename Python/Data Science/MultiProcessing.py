import multiprocessing as mp

def sub_process(name):
    print("[sub] start")
    cp = mp.current_process()
    print(f"[sub] pid : {cp.pid}")
    print(name)
    print("[sub] end")
    

if __name__ == "__main__":
    print("[main] start")
    p = mp.Process(target=sub_process, args = ('startcoding',))
    p.start()
    cp = mp.current_process()
    print(f"[main] pid : {cp.pid}")
    print("[main] end")