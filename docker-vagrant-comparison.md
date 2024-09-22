# Measuring performance of an application across Docker and Virtual Machine provisioned using Vagrant

The aim of this activity is to benchmark and compare the performance of a Java-based web application across two distinct environments: a Docker container and a Virtual Machine provisioned using Vagrant.

To evaluate performance, we will measure key metrics, including request throughput, CPU usage, and memory consumption, in both environments. The benchmarking process will involve simulating a load of 100,000 requests with a concurrency level of 100 requests using the Apache Bench tool. For monitoring CPU and memory usage, we will be using VisualVM, a GUI-based profiler for the Java Virtual Machine which uses JMX for profiling.

## Docker

### Setup 
1. Start the docker container and note down application startup time.
   ```bash
   docker run -d -p 8080:8080 md5-checksum-app
   ```
   <img width="1440" alt="Screenshot 2024-09-21 at 6 40 06 PM" src="https://github.com/user-attachments/assets/6ac7be81-9a66-4862-932d-904f99ce6541">

2. Start VisualVM to start profiling the JVM metrics.
3. Run benchmark using Apache Bench with a load of 100000 requests with concurency of 100 requests using a sample template request.
   ```bash
   ab -p misc/sampleRequestBody.json -T application/json -l -c 100 -n 100000 http://localhost:8080/api/checksum/md5
   ```
   
### Results

Startup Time : 0.876 seconds
```
This is ApacheBench, Version 2.3 <$Revision: 1913912 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 10000 requests
Completed 20000 requests
Completed 30000 requests
Completed 40000 requests
Completed 50000 requests
Completed 60000 requests
Completed 70000 requests
Completed 80000 requests
Completed 90000 requests
Completed 100000 requests
Finished 100000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /api/checksum/md5
Document Length:        Variable

Concurrency Level:      100
Time taken for tests:   10.898 seconds
Complete requests:      100000
Failed requests:        0
Total transferred:      15200000 bytes
Total body sent:        17900000
HTML transferred:       4700000 bytes
Requests per second:    9176.05 [#/sec] (mean)
Time per request:       10.898 [ms] (mean)
Time per request:       0.109 [ms] (mean, across all concurrent requests)
Transfer rate:          1362.07 [Kbytes/sec] received
                        1604.02 kb/s sent
                        2966.09 kb/s total

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.9      0      75
Processing:     0   10  21.4      7    1010
Waiting:        0    9  21.3      7    1010
Total:          0   10  21.4      7    1010

Percentage of the requests served within a certain time (ms)
  50%      7
  66%      8
  75%      9
  80%     10
  90%     12
  95%     15
  98%     36
  99%     61
 100%   1010 (longest request)
```
<img width="1440" alt="Screenshot 2024-09-21 at 6 43 02 PM" src="https://github.com/user-attachments/assets/1236ce2f-fefa-425c-93d0-24253b18e43d">
<img width="1433" alt="Screenshot 2024-09-21 at 6 43 14 PM" src="https://github.com/user-attachments/assets/b8a7684d-5eae-456c-8ad3-8eb4cf46ede1">







## Virtual Machine using Vagrant

### Setup 
1. Prepare the Vagrantfile to build and deploy the application on VM.
   <img width="1440" alt="Screenshot 2024-09-21 at 7 38 15 PM" src="https://github.com/user-attachments/assets/99abf2b5-5e97-4af2-aafd-5cc5a612bb7b"> 
2. Boot up the application virtual machine using vagrant
   ```bash
   vagrant up
   ```
   <img width="1440" alt="Screenshot 2024-09-21 at 6 45 22 PM" src="https://github.com/user-attachments/assets/e9eb2864-b5f6-4541-a66f-e7207f66a20a">
   <img width="1440" alt="Screenshot 2024-09-21 at 6 52 12 PM" src="https://github.com/user-attachments/assets/bd6153cd-485d-4377-9c14-964ecf02b86b">
   
3. Start VisualVM to start profiling JVM metrics.
4. Run benchmark using Apache Bench with a load of 100000 requests with concurency of 100 requests using a sample template request.
   ```bash
   ab -p misc/sampleRequestBody.json -T application/json -l -c 100 -n 100000 http://localhost:8080/api/checksum/md5
   ```
   
### Results

Startup Time : 4.042 seconds
```
This is ApacheBench, Version 2.3 <$Revision: 1913912 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 10000 requests
Completed 20000 requests
Completed 30000 requests
Completed 40000 requests
Completed 50000 requests
Completed 60000 requests
Completed 70000 requests
Completed 80000 requests
Completed 90000 requests
Completed 100000 requests
Finished 100000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /api/checksum/md5
Document Length:        Variable

Concurrency Level:      100
Time taken for tests:   442.052 seconds
Complete requests:      100000
Failed requests:        0
Total transferred:      15200000 bytes
Total body sent:        17900000
HTML transferred:       4700000 bytes
Requests per second:    226.22 [#/sec] (mean)
Time per request:       442.052 [ms] (mean)
Time per request:       4.421 [ms] (mean, across all concurrent requests)
Transfer rate:          33.58 [Kbytes/sec] received
                        39.54 kb/s sent
                        73.12 kb/s total

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.2      0      21
Processing:    15  442 1666.1    122   13972
Waiting:        1  440 1666.3    121   13971
Total:         17  442 1666.1    122   13972

Percentage of the requests served within a certain time (ms)
  50%    122
  66%    128
  75%    134
  80%    140
  90%    194
  95%    630
  98%   6870
  99%  13182
 100%  13972 (longest request)
```

<img width="1440" alt="Screenshot 2024-09-21 at 6 57 28 PM" src="https://github.com/user-attachments/assets/372c4388-1168-404f-a081-e9f6dddde0f5">
<img width="1435" alt="Screenshot 2024-09-21 at 6 57 35 PM" src="https://github.com/user-attachments/assets/9c97b8eb-13f9-4ea0-a84b-0570384f519a">

## Comparison

| **Metric**                         | **Docker Container**             | **Virtual Machine (Vagrant)**     |
|-------------------------------------|----------------------------------|----------------------------------|
| **Startup Time**                    | 0.876 seconds                    | 4.042 seconds                    |
| **Concurrency Level**               | 100                              | 100                              |
| **Time taken for tests (seconds)**  | 10.898                           | 442.052                          |
| **Complete requests**               | 100,000                          | 100,000                          |
| **Failed requests**                 | 0                                | 0                                |
| **Requests per second (mean)**      | 9,176.05 [#/sec]                 | 226.22 [#/sec]                   |
| **Time per request (mean)**         | 10.898 ms                        | 442.052 ms                       |
| **Time per request (mean, across all concurrent requests)** | 0.109 ms               | 4.421 ms                        |
| **Total transferred (bytes)**       | 15,200,000                       | 15,200,000                       |
| **Total body sent (bytes)**         | 17,900,000                       | 17,900,000                       |
| **HTML transferred (bytes)**        | 4,700,000                        | 4,700,000                        |
| **Transfer rate (received)**        | 1362.07 KB/sec                   | 33.58 KB/sec                     |
| **Transfer rate (sent)**            | 1604.02 KB/sec                   | 39.54 KB/sec                     |
| **Transfer rate (total)**           | 2966.09 KB/sec                   | 73.12 KB/sec                     |
| **Median request time**             | 7 ms                             | 122 ms                           |
| **Longest request time**            | 1010 ms                          | 13,972 ms                        |
| **50% of requests served within**   | 7 ms                             | 122 ms                           |
| **90% of requests served within**   | 12 ms                            | 194 ms                           |
| **99% of requests served within**   | 61 ms                            | 13,182 ms                        |
| **100% of requests served within**  | 1010 ms                          | 13,972 ms                        |


<table>
   <tr>
      <td> Running on Docker Container </td>
      <td> Running on Virtual Machine using Vagrant </td>
  <tr>
      <td><img width="531" alt="Screenshot 2024-09-21 at 6 43 14 PM" src="https://github.com/user-attachments/assets/2400fb37-7a88-4ed0-9f67-16e062025d88"></td>
      <td><img width="534" alt="Screenshot 2024-09-21 at 6 57 35 PM" src="https://github.com/user-attachments/assets/8b9934ac-f0e1-42e6-a8d9-e4b9c656145d"></td>
   </tr> 
   <tr>
      <td> <img width="534" alt="Screenshot 2024-09-21 at 6 43 14 PM copy" src="https://github.com/user-attachments/assets/a9a92d71-5a46-45dc-b790-3354373d57ab"></td>
      <td><img width="531" alt="Screenshot 2024-09-21 at 6 57 35 PM copy" src="https://github.com/user-attachments/assets/e8d7eb63-7eb7-438f-abdc-e375147fa08f"></td>
  </tr>
</table>


## Summary

Docker container processed 9,176.05 requests per second while the VM handled 226.22 requests per second. Additionally, the startup time for the application in Docker was just 0.876 seconds, compared to 4.042 seconds for the VM, indicating a quicker deployment process. This significant difference suggests that the overhead associated with virtualization in the VM may be impacting its performance. In terms of median request time, Docker recorded 7 ms while the VM had a median of 122 ms. We can also observe that Docker instance has a higher memory consumption but we cannot consider it as drawback of Docker as the higher memory usage can be attributed to higher throughput processed by Docker instance.

Overall, Docker's lighter approach to containerization, compared to the more resource-heavy virtualization in Vagrant, makes it a faster and more efficient way to manage applications. Docker also aligns better with current cloud-native trends, offering a more up-to-date and faster solution for modern deployment environments.
