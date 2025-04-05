import cv2
import numpy as np
import os

# Video Example: https://www.youtube.com/watch?v=cAQyX-i4jyY
# Path to input video
video_path = "Plaid Apple!!.mp4"

# Open video
cap = cv2.VideoCapture(video_path)
fps = int(cap.get(cv2.CAP_PROP_FPS))  # e.g. 30
total_frames = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
frame_width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
frame_height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))

print(f"[+] FPS: {fps}, Total Frames: {total_frames}, Resolution: {frame_width}x{frame_height}")

# Create output folder
os.makedirs("masks", exist_ok=True)

sec = 0
while True:
    frames = []
    for _ in range(fps):  # Collect 1 second worth of frames
        ret, frame = cap.read()
        if not ret:
            break
        frames.append(frame.astype(np.float32))

    if len(frames) == 0:
        break

    # Stack and compute std deviation
    frames_np = np.stack(frames, axis=0)  # (fps, H, W, C)
    std_dev = np.std(frames_np, axis=0)
    std_gray = np.mean(std_dev, axis=2)  # (H, W)

    # Threshold and mask
    threshold = 50  # Adjust based on your needs
    mask = (std_gray < threshold).astype(np.uint8) * 255

    # Save mask
    out_path = f"masks/second_{sec:04d}.png"
    cv2.imwrite(out_path, mask)
    print(f"[+] Saved mask for second {sec}")

    sec += 1

cap.release()
print("✅ All done!")
