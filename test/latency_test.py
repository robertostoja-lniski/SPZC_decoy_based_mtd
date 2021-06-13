import requests
import time
import random
import string
from multiprocessing.dummy import Pool
from datetime import datetime

SERVER_ADDR_AND_PORT = 'http://127.0.0.1:8080/'
SERVER_ADDR_AND_PORT = 'http://35.239.164.41:8080/'

def get_random_string(length):
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(length))

def multi_test():

        pool = Pool(5)
        futures = []
        ok_count = 0
        nok_count = 0

        start = time.time()

        futures.append(pool.apply_async(requests.post, [f'{SERVER_ADDR_AND_PORT}user?name={get_random_string(5)}&pwdHash=abbaw&loginTrial=Submit']))
        futures.append(pool.apply_async(requests.post, [f'{SERVER_ADDR_AND_PORT}user?name={get_random_string(5)}&pwdHash=abbaw&loginTrial=Submit']))
        futures.append(pool.apply_async(requests.post, [f'{SERVER_ADDR_AND_PORT}user?name={get_random_string(5)}&pwdHash=abbaw&loginTrial=Submit']))
        futures.append(pool.apply_async(requests.post, [f'{SERVER_ADDR_AND_PORT}user?name={get_random_string(5)}&pwdHash=abbaw&loginTrial=Submit']))
        futures.append(pool.apply_async(requests.post, [f'{SERVER_ADDR_AND_PORT}user?name={get_random_string(5)}&pwdHash=abbaw&loginTrial=Submit']))

        for future in futures:
            response = future.get()
            if response.status_code == 200:
                ok_count += 1
            else:
                nok_count += 1


        end = time.time()

        file_object = open('latency_test_result.txt', 'a')
        file_object.write(f'{datetime.now()}\t{(end-start)/5}\tok:{ok_count}\tnok{nok_count}')
        file_object.write("\n")
        file_object.close()

        print(f'{datetime.now()}\t{(end-start)/5}\tok:{ok_count}\tnok{nok_count}')
        time.sleep(2)

while 1:
    multi_test()



