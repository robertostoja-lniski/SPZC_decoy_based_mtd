import requests
import time
import random
import string
from multiprocessing.dummy import Pool

def get_random_string(length):
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(length))

TEST_NUM = 100

def multi_test(test_trials):

    results = {}
    for trial in test_trials:
        random_users = []
        for i in range(1, trial):
            random_users.append(get_random_string(5))

        pool = Pool(trial)

        for i in range(0, trial -1):
             current_name = random_users[i]
             futures = []
             futures.append(pool.apply_async(requests.post, [f'http://127.0.0.1:8080/user?name={current_name}&pwdHash=abbaw&loginTrial=Submit']))

        start = time.time()
        futures.append(pool.apply_async(requests.post, [f'http://127.0.0.1:8080/user?name={current_name}&pwdHash=abbaw&loginTrial=Submit']))
        futures.append(pool.apply_async(requests.post, [f'http://127.0.0.1:8080/user?name={current_name}&pwdHash=abbaw&loginTrial=Submit']))
        futures.append(pool.apply_async(requests.post, [f'http://127.0.0.1:8080/user?name={current_name}&pwdHash=abbaw&loginTrial=Submit']))
        futures.append(pool.apply_async(requests.post, [f'http://127.0.0.1:8080/user?name={current_name}&pwdHash=abbaw&loginTrial=Submit']))
        futures.append(pool.apply_async(requests.post, [f'http://127.0.0.1:8080/user?name={current_name}&pwdHash=abbaw&loginTrial=Submit']))

        for future in futures:
             print(future.get())

        end = time.time()
        # mierzymy czas 5 ostatnich zgloszen
        print(f"Dla {trial} zgloszen czas wynosi: {(end - start) / 5}")
        results[trial] = (end - start) / 5

        time.sleep(2)

    return results

results = multi_test([100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000])
print(f"Wyniki postaci liczba testow: czas obslugi zgloszenia")
for test_number, result in results.items():
    print(result)
