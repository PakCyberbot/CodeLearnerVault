# LAB LINK: https://cybertalents.com/challenges/forensics/can-you-find-me

# Installation of the above programs first if it isn't installed
# bunzip2
# gunzip
# unzip
# tar
# nomarch
# 7z
# arj
# kgb
# xz
# cabextract
# rzip
# ppmd from https://launchpad.net/ubuntu/+source/ppmd, ppmd version of kali won't support the ppmd archive in this challenge
# zoo from https://launchpad.net/ubuntu/+source/zoo
import subprocess
import os

def check_filetype():
    command = 'file secret | cut -d \' \' -f 2'

    filetype = subprocess.run(command, shell=True, check=True, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    
    return filetype.stdout.removesuffix('\n')

if __name__ == "__main__":
    while True:
        if os.path.exists('secret'):
            filetype = check_filetype()
            print(filetype)
            if filetype == '7-zip':
                os.system(f'mv secret secret.7z')
                os.system(f'7z x secret.7z')
            elif filetype == 'PPMD':
                os.system(f'mv secret secret.ppmd')
                os.system(f'ppmd d secret.ppmd')
            elif filetype == 'ARC':
                os.system(f'mv secret secret.arc')
                os.system(f'nomarch secret.arc')
            elif filetype == 'Microsoft':
                os.system(f'mv secret secret.cab')
                os.system(f'cabextract secret.cab')
            elif filetype == 'POSIX':
                os.system(f'mv secret secret.tar')
                os.system(f'tar -xf secret.tar')
            elif filetype == 'Microsoft':
                os.system(f'mv secret secret.cab')
                os.system(f'cabextract secret.cab')
            elif filetype == 'bzip2':
                os.system(f'mv secret secret.bz')
                os.system(f'bunzip2 secret.bz')
            elif filetype == 'XZ':
                os.system(f'mv secret secret.xz')
                os.system(f'xz -d secret.xz')
            elif filetype == 'KGB':
                os.system(f'kgb secret secret.kgb')
                os.system(f'kgb secret.kgb')
            elif filetype == 'ARJ':
                os.system(f'mv secret secret.arj')
                os.system(f'arj x secret.arj')
            elif filetype == 'rzip':
                os.system(f'mv secret secret.rz')
                os.system(f'rzip -d secret.rz')
            elif filetype == 'gzip':
                os.system(f'mv secret secret.gz')
                os.system(f'gunzip secret.gz')
            elif filetype == 'Zip':
                os.system(f'mv secret secret.zip')
                os.system(f'unzip secret.zip')
            elif filetype == 'Zoo':
                os.system(f'mv secret secret.zoo')
                os.system(f'zoo -extract secret.zoo')
            else:
                break
