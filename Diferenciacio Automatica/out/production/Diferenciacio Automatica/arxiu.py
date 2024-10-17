import numpy as np
import matplotlib.pyplot as plt
x = np.arange(0,4*np.pi,0.05)
# discretize the interval [0, 4\pi] : [0, 0.05, 0.10, 0.15 ... 12.55]
y = (x**2)*(np.sin(x)**2+np.cos(x))
plt.plot(x,y)
idx_maxima = np.where(np.logical_and( y[1:-1]-y[:-2] > 0, y[1:-1]-y[2:] > 0))[0] + 1
plt.plot(x[idx_maxima],y[idx_maxima],'go')
idx_minima = np.where(np.logical_and( y[1:-1]-y[:-2] < 0, y[1:-1]-y[2:] < 0))[0] + 1
plt.plot(x[idx_minima],y[idx_minima],'ro')
plt.title('$x^2 ( sin^2 x + cos x)$')
print('maxima at {}, minima at {}'.format(x[idx_maxima], x[idx_minima]))