import requests
import time
import random
import string
from multiprocessing.dummy import Pool
from datetime import datetime

SERVER_ADDR_AND_PORT = 'http://127.0.0.1:8080/'
ALT_SERVER_ADDR_AND_PORT = 'http://35.239.164.41:8080/'
SERVER_ADDR_AND_PORT = 'http://104.154.236.131:8080/'

def get_random_string(length):
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(length))

def multi_test(addr):

        pool = Pool(1)
        futures = []
        ok_count = 0
        nok_count = 0

        start = time.time()

        futures.append(pool.apply_async(requests.post, [f'{addr}user?name={get_random_string(5)}&pwdHash=abbaw&loginTrial=Submit']))
        for future in futures:
            response = future.get()
            if response.status_code == 200:
                ok_count += 1
            else:
                addr = ALT_SERVER_ADDR_AND_PORT
                nok_count += 1


        end = time.time()

        file_object = open('latency_test_result.txt', 'a')
        file_object.write(f'{datetime.now()}\t{(end-start)/5}\tok:{ok_count}\tnok{nok_count}')
        file_object.write("\n")
        file_object.close()

        print(f'{datetime.now()}\t{(end-start)/5}\tok:{ok_count}\tnok{nok_count}')
        time.sleep(0.1)

        return addr


addr = SERVER_ADDR_AND_PORT
while 1:
    addr = multi_test(addr)



