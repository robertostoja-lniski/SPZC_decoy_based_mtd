import requests
import time
import random
import string

def get_random_string(length):
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(length))

TEST_NUM=100000

random_users = []
for i in range(1, TEST_NUM):
    random_users.append(get_random_string(5))


start = time.time()
for i in range(0, TEST_NUM -1):
    current_name = random_users[i]
    res = requests.post(f'http://127.0.0.1:8080/user?name={current_name}&pwdHash=abbaw&email=tmp@gmail.com&createAccountTrial=Submit')


end = time.time()
print(f"Single get response time is: {(end - start) / TEST_NUM}")